package com.gupaoedu.gpmall.marking.dto;

import com.gupaoedu.gpmall.core.AbstractResponse;
import lombok.Data;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Data
public class DrawResponse extends AbstractResponse {



    //奖品名称
    private String prizeName;
    //中奖登记
    private Integer level;
    //奖品id
    private Integer prizeId;
}
