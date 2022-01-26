package com.gupaoedu.gpmall.marking.markingserviceprovider.event;

import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.mapper.MmsLotteryChanceMapper;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryChance;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "marking-service",topic = "user_register")
public class UserRegisterQueueListener implements RocketMQListener<Integer> {

    @Autowired
    MmsLotteryChanceMapper lotteryChanceMapper;

    @Override
    public void onMessage(Integer uid) {
        log.info("begin UserRegisterQueueListener, receive message: {}",uid);
        //如果重复收到消息的情况下，要处理幂等性。
        //redis.setNx() ; 符合原子性的
        //redis.inc(); 判断是非原子的。
        try {
            MmsLotteryChance lotteryChance = new MmsLotteryChance();
            lotteryChance.setCreateDate(LocalDateTime.now());
            lotteryChance.setMaxLimit(1);
            lotteryChance.setNowLimit(0);
            lotteryChance.setUid(uid);
            lotteryChanceMapper.insert(lotteryChance); //insert
        }catch (DuplicateKeyException e){
            lotteryChanceMapper.incrementLotteryChance(uid); //increment。（存在重复增加）
        }
    }
}
