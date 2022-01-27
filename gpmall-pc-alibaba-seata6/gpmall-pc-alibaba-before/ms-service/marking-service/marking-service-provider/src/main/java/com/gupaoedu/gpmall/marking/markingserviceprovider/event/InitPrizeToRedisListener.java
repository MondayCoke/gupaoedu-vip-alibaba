package com.gupaoedu.gpmall.marking.markingserviceprovider.event;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.mapper.MmsLotteryItemMapper;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.mapper.MmsLotteryPrizeMapper;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryItem;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryPrize;
import com.gupaoedu.gpmall.marking.markingserviceprovider.utils.constants.RedisKeyManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
@Component
public class InitPrizeToRedisListener implements ApplicationListener<InitPrizeToRedisEvent> {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    MmsLotteryPrizeMapper lotteryPrizeMapper;
    @Autowired
    MmsLotteryItemMapper lotteryItemMapper;

    @Async
    @Override
    public void onApplicationEvent(InitPrizeToRedisEvent initPrizeToRedisEvent) {
        log.info("begin InitPrizeToRedisListener,"+initPrizeToRedisEvent);
        Boolean result=redisTemplate.opsForValue().setIfAbsent(RedisKeyManager.getLotteryPrizeRedisKey(initPrizeToRedisEvent.getLotteryId()),"1");
        //已经初始化到缓存中了，不需要再次缓存
        if(!result){
            log.info("already initial");
            initPrizeToRedisEvent.getCountDownLatch().countDown();
            return;
        }
        QueryWrapper<MmsLotteryItem> lotteryItemQueryWrapper = new QueryWrapper<>();
        lotteryItemQueryWrapper.eq("lottery_id", initPrizeToRedisEvent.getLotteryId());
        List<MmsLotteryItem> lotteryItems=lotteryItemMapper.selectList(lotteryItemQueryWrapper);

        //如果指定的奖品没有了，会生成一个默认的奖项
        MmsLotteryItem defaultLotteryItem=lotteryItems.parallelStream().filter(o->o.getDefaultItem().intValue()==1).findFirst().orElse(null);

        Map<String,Object> lotteryItemMap=new HashMap<>(16);
        lotteryItemMap.put(RedisKeyManager.getLotteryItemRedisKey(initPrizeToRedisEvent.getLotteryId()),lotteryItems);
        lotteryItemMap.put(RedisKeyManager.getDefaultLotteryItemRedisKey(initPrizeToRedisEvent.getLotteryId()),defaultLotteryItem);
        redisTemplate.opsForValue().multiSet(lotteryItemMap);

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("lottery_id",initPrizeToRedisEvent.getLotteryId());
        List<MmsLotteryPrize> lotteryPrizes=lotteryPrizeMapper.selectList(queryWrapper);

        //保存一个默认奖项
        AtomicReference<MmsLotteryPrize> defaultPrize=new AtomicReference<>();
        lotteryPrizes.stream().forEach(lotteryPrize -> {
            if(lotteryPrize.getId().equals(defaultLotteryItem.getPrizeId())){
                defaultPrize.set(lotteryPrize);
            }
            String key=RedisKeyManager.getLotteryPrizeRedisKey(initPrizeToRedisEvent.getLotteryId(),lotteryPrize.getId());
            setLotteryPrizeToRedis(key,lotteryPrize);
        });
        String key=RedisKeyManager.getDefaultLotteryPrizeRedisKey(initPrizeToRedisEvent.getLotteryId());
        setLotteryPrizeToRedis(key,defaultPrize.get());
        initPrizeToRedisEvent.getCountDownLatch().countDown(); //表示初始化完成
        log.info("finish InitPrizeToRedisListener,"+initPrizeToRedisEvent);
    }

    private void setLotteryPrizeToRedis(String key,MmsLotteryPrize lotteryPrize){
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.opsForHash().put(key,"id",lotteryPrize.getId());
        redisTemplate.opsForHash().put(key,"lotteryId",lotteryPrize.getLotteryId());
        redisTemplate.opsForHash().put(key,"prizeName",lotteryPrize.getPrizeName());
        redisTemplate.opsForHash().put(key,"prizeType",lotteryPrize.getPrizeType());
        redisTemplate.opsForHash().put(key,"totalStock",lotteryPrize.getTotalStock());
        redisTemplate.opsForHash().put(key,"validStock",lotteryPrize.getValidStock());
    }
}

