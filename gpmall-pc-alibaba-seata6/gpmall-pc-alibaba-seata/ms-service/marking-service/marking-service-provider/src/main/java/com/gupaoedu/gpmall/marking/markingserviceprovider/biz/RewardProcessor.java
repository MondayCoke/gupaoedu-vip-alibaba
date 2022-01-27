package com.gupaoedu.gpmall.marking.markingserviceprovider.biz;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public interface RewardProcessor<T> {

    void doReward(RewardContext context);
}
