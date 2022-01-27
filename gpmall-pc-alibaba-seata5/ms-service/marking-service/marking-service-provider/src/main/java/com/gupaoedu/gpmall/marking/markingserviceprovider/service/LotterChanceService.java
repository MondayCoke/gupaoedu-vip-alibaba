package com.gupaoedu.gpmall.marking.markingserviceprovider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gupaoedu.gpmall.marking.ILotterChanceService;
import com.gupaoedu.gpmall.marking.dto.UserDrawChanceRequest;
import com.gupaoedu.gpmall.marking.dto.UserDrawChanceResponse;
import com.gupaoedu.gpmall.marking.enums.MmsResCodeEnum;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.mapper.MmsLotteryChanceMapper;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryChance;
import com.gupaoedu.gpmall.marking.markingserviceprovider.utils.exception.MmsExceptionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDateTime;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Slf4j
@DubboService
public class LotterChanceService implements ILotterChanceService {

    @Autowired
    MmsLotteryChanceMapper lotteryChanceMapper;

    @Override
    public UserDrawChanceResponse queryUserDrawChance(UserDrawChanceRequest request) {
        log.info("[LotterChanceService.queryUserDrawChance], request : {}",request);
        UserDrawChanceResponse response=new UserDrawChanceResponse();
        try{
            request.checkParam();
            QueryWrapper<MmsLotteryChance> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("uid",request.getUid());
            MmsLotteryChance lotteryChance=lotteryChanceMapper.selectOne(queryWrapper);
            if(lotteryChance==null){
                response.setCode(MmsResCodeEnum.REQUEST_DATA_NOT_EXIST.getCode());
                response.setMsg(MmsResCodeEnum.REQUEST_DATA_NOT_EXIST.getMsg());
                return response;
            }
            response.setUid(lotteryChance.getUid());
            response.setMaxLimit(lotteryChance.getMaxLimit());
            response.setNowLimit(lotteryChance.getNowLimit());
            response.setCode(MmsResCodeEnum.SYS_SUCCESS.getCode());
            response.setMsg(MmsResCodeEnum.SYS_SUCCESS.getMsg());
        }catch (Exception e){
            log.error("[LotterChanceService.queryUserDrawChance], occur Exception",e);
            MmsExceptionWrapper.handlerException4biz(response,e);
        }
        return response;
    }

    @Override
    public UserDrawChanceResponse incrementDrawChance(UserDrawChanceRequest request) {
        UserDrawChanceResponse response=new UserDrawChanceResponse();
        response.setCode(MmsResCodeEnum.SYS_SUCCESS.getCode());
        response.setMsg(MmsResCodeEnum.SYS_SUCCESS.getMsg());
        try {
            MmsLotteryChance lotteryChance = new MmsLotteryChance();
            lotteryChance.setCreateDate(LocalDateTime.now());
            lotteryChance.setMaxLimit(1);
            lotteryChance.setNowLimit(0);
            lotteryChance.setUid(request.getUid());
            lotteryChanceMapper.insert(lotteryChance); //insert
        }catch (DuplicateKeyException e){
            lotteryChanceMapper.incrementLotteryChance(request.getUid()); //increment。（存在重复增加）
        }catch (Exception e){
            log.error("[LotterChanceService.incrementDrawChance], occur Exception",e);
            MmsExceptionWrapper.handlerException4biz(response,e);
        }
        return response;
    }
}
