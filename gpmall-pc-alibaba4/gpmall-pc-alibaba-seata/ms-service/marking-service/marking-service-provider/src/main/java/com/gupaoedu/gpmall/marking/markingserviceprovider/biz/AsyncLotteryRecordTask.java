package com.gupaoedu.gpmall.marking.markingserviceprovider.biz;

import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.mapper.MmsLotteryRecordMapper;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryItem;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
@Component
public class AsyncLotteryRecordTask {

    @Autowired
    MmsLotteryRecordMapper lotteryRecordMapper;

    @Async("lotteryServiceExecutor")
    public void saveLotteryRecord(String accountIp, MmsLotteryItem lotteryItem, String prizeName){
        log.info(Thread.currentThread().getName()+"---saveLotteryRecord");
        //存储中奖信息
        MmsLotteryRecord record=new MmsLotteryRecord();
        record.setAccountIp(accountIp);
        record.setItemId(lotteryItem.getId());
        record.setPrizeName(prizeName);
        record.setCreateTime(LocalDateTime.now());
        lotteryRecordMapper.insert(record);
    }
}
