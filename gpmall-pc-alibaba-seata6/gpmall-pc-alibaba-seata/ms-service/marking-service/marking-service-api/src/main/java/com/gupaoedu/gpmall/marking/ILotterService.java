package com.gupaoedu.gpmall.marking;

import com.gupaoedu.gpmall.marking.dto.DrawRequest;
import com.gupaoedu.gpmall.marking.dto.DrawResponse;
import com.gupaoedu.gpmall.marking.dto.UserDrawChanceRequest;
import com.gupaoedu.gpmall.marking.dto.UserDrawChanceResponse;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public interface ILotterService {

    /**
     * 开始抽奖
     * @param request
     * @return
     */
    DrawResponse doDraw(DrawRequest request);


}
