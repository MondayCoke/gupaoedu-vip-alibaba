package com.gupaoedu.gpmall.marking.dto;

import com.gupaoedu.gpmall.core.AbstractRequest;
import com.gupaoedu.gpmall.exception.ValidException;
import com.gupaoedu.gpmall.marking.enums.MmsResCodeEnum;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Data
public class DrawRequest extends AbstractRequest {

    //活动id
    private Integer lotteryId;
    //ip
    private String account;

}
