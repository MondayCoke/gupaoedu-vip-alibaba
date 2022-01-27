package com.gupaoedu.gpmall.marking.dto;

import com.gupaoedu.gpmall.core.AbstractRequest;
import com.gupaoedu.gpmall.core.AbstractResponse;
import lombok.Data;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 * 查询抽奖次数
 **/
@Data
public class UserDrawChanceResponse extends AbstractResponse {

    private Integer uid; //uid
    private Integer nowLimit; //当前抽奖次数
    private Integer maxLimit; //最大抽奖次数
}
