package com.gupaoedu.gpmall.marking.markingserviceprovider.biz;

import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLottery;
import com.gupaoedu.gpmall.marking.markingserviceprovider.dal.model.MmsLotteryItem;
import lombok.Data;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Data
public class RewardContext {

    private MmsLottery lottery;
    private MmsLotteryItem lotteryItem;

    private String key;

    private String accountIp;

    private String prizeName;
    private Integer level;
    private Integer prizeId;
}
