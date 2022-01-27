package com.gupaoedu.gpmall.marking.markingserviceprovider.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gupaoedu.gpmall.exception.BizException;
import com.gupaoedu.gpmall.marking.ILotterService;
import com.gupaoedu.gpmall.marking.dto.DrawRequest;
import com.gupaoedu.gpmall.marking.dto.DrawResponse;
import com.gupaoedu.gpmall.marking.dto.UserDrawChanceRequest;
import com.gupaoedu.gpmall.marking.dto.UserDrawChanceResponse;
import com.gupaoedu.gpmall.marking.enums.MmsResCodeEnum;
import com.gupaoedu.gpmall.marking.markingserviceprovider.biz.AbstractRewardProcessor;
import com.gupaoedu.gpmall.marking.markingserviceprovider.biz.RewardContext;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.mapper.*;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLottery;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryChance;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryItem;
import com.gupaoedu.gpmall.marking.markingserviceprovider.event.InitPrizeToRedisEvent;
import com.gupaoedu.gpmall.marking.markingserviceprovider.utils.constants.LotteryConstants;
import com.gupaoedu.gpmall.marking.markingserviceprovider.utils.constants.RedisKeyManager;
import com.gupaoedu.gpmall.marking.markingserviceprovider.utils.exception.MmsExceptionWrapper;
import com.gupaoedu.gpmall.marking.markingserviceprovider.utils.exception.RewardException;
import com.gupaoedu.gpmall.marking.markingserviceprovider.utils.exception.UnRewardException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Slf4j
@DubboService
public class LotterService implements ILotterService {

    @Autowired
    MmsLotteryMapper lotteryMapper;
    @Autowired
    MmsLotteryPrizeMapper lotteryPrizeMapper;
    @Autowired
    MmsLotteryRecordMapper mmsLotteryRecordMapper;
    @Autowired
    MmsLotteryItemMapper lotteryItemMapper;
    @Autowired
    MmsLotteryChanceMapper lotteryChanceMapper;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    RedisTemplate redisTemplate;

    private static final int mulriple = 10000;

    @Override
    public DrawResponse doDraw(DrawRequest request) {
        log.info("[LotterService.doDraw, request : {}",request);
        DrawResponse response=new DrawResponse();
        RewardContext context = new RewardContext();
        MmsLotteryItem lotteryItem = null;
        try {
            checkDrawParams(request);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            //判断活动有效性
            MmsLottery lottery = checkLottery(request);
            //发布事件，用来加载指定活动的奖品信息
            applicationContext.publishEvent(new InitPrizeToRedisEvent(this,lottery.getId(), countDownLatch));
            //开始抽奖
            lotteryItem = doPlay(lottery);
            //记录奖品并扣减库存
            countDownLatch.await(); //等待奖品初始化完成
            String key = RedisKeyManager.getLotteryPrizeRedisKey(lottery.getId(), lotteryItem.getPrizeId());
            int prizeType = Integer.parseInt(redisTemplate.opsForHash().get(key, "prizeType").toString());
            context.setLottery(lottery);
            context.setLotteryItem(lotteryItem);
            context.setAccountIp(request.getAccount());
            context.setKey(key);
            //调整库存及记录中奖信息
            AbstractRewardProcessor.rewardProcessorMap.get(prizeType).doReward(context);
            //拼接返回数据
            response.setLevel(lotteryItem.getLevel());
            response.setPrizeName(context.getPrizeName());
            response.setPrizeId(context.getPrizeId());
            response.setCode(MmsResCodeEnum.SYS_SUCCESS.getCode());
            response.setMsg(MmsResCodeEnum.SYS_SUCCESS.getMsg());
        } catch (UnRewardException u) { //表示因为某些问题未中奖，返回一个默认奖项
            context.setKey(RedisKeyManager.getDefaultLotteryPrizeRedisKey(lotteryItem.getLotteryId()));
            lotteryItem=(MmsLotteryItem) redisTemplate.opsForValue().get(RedisKeyManager.getDefaultLotteryItemRedisKey(lotteryItem.getLotteryId()));
            context.setLotteryItem(lotteryItem);
            AbstractRewardProcessor.rewardProcessorMap.get(LotteryConstants.PrizeTypeEnum.THANK.getValue()).doReward(context);
        }catch (Exception e){
            log.error("[LotterService.doDraw], occur Exception",e);
            MmsExceptionWrapper.handlerException4biz(response,e);
        }finally {
            //清除占位标记
            redisTemplate.delete(RedisKeyManager.getDrawingRedisKey(request.getAccount()));
        }
        return response;
    }


    private void checkDrawParams(DrawRequest request){
        if(null==request.getLotteryId()|| StringUtils.isEmpty(request.getAccount())){
            throw new RewardException(MmsResCodeEnum.SYS_PARAM_NOT_NULL.getCode(),MmsResCodeEnum.SYS_PARAM_NOT_NULL.getMsg());
        }
        //采用setNx命令，判断当前用户上一次抽奖是否结束
        Boolean result=redisTemplate.opsForValue().setIfAbsent(RedisKeyManager.getDrawingRedisKey(request.getAccount()),"1", 60, TimeUnit.SECONDS);
        //如果为false，说明上一次抽奖还未结束
        if(!result){
            throw new RewardException(MmsResCodeEnum.LOTTER_DRAWING.getCode(),MmsResCodeEnum.LOTTER_DRAWING.getMsg());
        }
    }

    /**
     * 校验当前活动的有效信息
     *
     * @param request
     * @return
     */
    private MmsLottery checkLottery(DrawRequest request) {
        MmsLottery lottery;
        Object lotteryJsonStr = redisTemplate.opsForValue().get(RedisKeyManager.getLotteryRedisKey(request.getLotteryId()));
        if (null != lotteryJsonStr) {
            lottery = JSON.parseObject(lotteryJsonStr.toString(), MmsLottery.class);
        } else {
            lottery = lotteryMapper.selectById(request.getLotteryId());
        }
        if (lottery == null) {
            throw new BizException(MmsResCodeEnum.LOTTER_NOT_EXIST.getCode(), MmsResCodeEnum.LOTTER_NOT_EXIST.getMsg());
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(lottery.getStartTime()) || now.isAfter(lottery.getEndTime())) {
            throw new BizException(MmsResCodeEnum.LOTTER_FINISH.getCode(), MmsResCodeEnum.LOTTER_FINISH.getMsg());
        }
        //判断并更新抽奖次数
        int rows=lotteryChanceMapper.updateLotteryChance(Integer.parseInt(request.getAccount()));
        if(rows<=0){ //不为空，
            throw new BizException(MmsResCodeEnum.NOT_ENOUGH_LOTTER_CHANCE.getCode(), MmsResCodeEnum.NOT_ENOUGH_LOTTER_CHANCE.getMsg());
        }
        return lottery;
    }

    //执行抽奖
    private MmsLotteryItem doPlay(MmsLottery lottery) {
        MmsLotteryItem lotteryItem = null;
        QueryWrapper<MmsLotteryItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lottery_id", lottery.getId());
        Object lotteryItemsObj=redisTemplate.opsForValue().get(RedisKeyManager.getLotteryItemRedisKey(lottery.getId()));
        List<MmsLotteryItem> lotteryItems;
        //说明还未加载到缓存中，同步从数据库加载，并且异步将数据缓存
        if(lotteryItemsObj==null){
            lotteryItems = lotteryItemMapper.selectList(queryWrapper);
        }else{
            lotteryItems=(List<MmsLotteryItem>)lotteryItemsObj;
        }
        //奖项数据未配置
        if (lotteryItems.isEmpty()) {
            throw new BizException(MmsResCodeEnum.LOTTER_ITEM_NOT_INITIAL.getCode(), MmsResCodeEnum.LOTTER_ITEM_NOT_INITIAL.getMsg());
        }
        int lastScope = 0;
        Collections.shuffle(lotteryItems);
        Map<Integer, int[]> awardItemScope = new HashMap<>();
        //item.getPercent=0.05 = 5%
        for (MmsLotteryItem item : lotteryItems) {
            int currentScope = lastScope + new BigDecimal(item.getPercent().floatValue()).multiply(new BigDecimal(mulriple)).intValue();
            awardItemScope.put(item.getId(), new int[]{lastScope + 1, currentScope});
            lastScope = currentScope;
        }
        int luckyNumber = new Random().nextInt(mulriple);
        int luckyPrizeId = 0;
        if (!awardItemScope.isEmpty()) {
            Set<Map.Entry<Integer, int[]>> set = awardItemScope.entrySet();
            for (Map.Entry<Integer, int[]> entry : set) {
                if (luckyNumber >= entry.getValue()[0] && luckyNumber <= entry.getValue()[1]) {
                    luckyPrizeId = entry.getKey();
                    break;
                }
            }
        }
        for (MmsLotteryItem item : lotteryItems) {
            if (item.getId().intValue() == luckyPrizeId) {
                lotteryItem = item;
                break;
            }
        }
        return lotteryItem;
    }
}
