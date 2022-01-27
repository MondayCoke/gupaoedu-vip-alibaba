package com.gupaoedu.gpmall.order.dto;

import com.gupaoedu.gpmall.core.AbstractRequest;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Data
public class CreateOrderRequest extends AbstractRequest {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 商品编码
     */
    private String commodityCode;

    /**
     * 订单数量
     */
    private Integer orderCount;

    /**
     * 消费总金额
     */
    private BigDecimal orderAmount;
}
