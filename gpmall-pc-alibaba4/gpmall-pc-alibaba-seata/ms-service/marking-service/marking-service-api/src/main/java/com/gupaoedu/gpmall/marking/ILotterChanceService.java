package com.gupaoedu.gpmall.marking;

import com.gupaoedu.gpmall.marking.dto.UserDrawChanceRequest;
import com.gupaoedu.gpmall.marking.dto.UserDrawChanceResponse;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public interface ILotterChanceService {

    /**
     * 查询指定用户的抽奖次数
     * @param request
     * @return
     */
    UserDrawChanceResponse queryUserDrawChance(UserDrawChanceRequest request);


    /**
     * 增加用户的抽奖机会
     * @param request
     * @return
     */
    UserDrawChanceResponse incrementDrawChance(UserDrawChanceRequest request);
}
