package com.gupaoedu.gpmall.user.dto;

import com.gupaoedu.gpmall.core.AbstractRequest;
import com.gupaoedu.gpmall.exception.ValidException;
import com.gupaoedu.gpmall.user.enums.UmsResCodeEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Data
public class DecrementAccountRequest extends AbstractRequest {

    private String userId;
    private BigDecimal amount;

    public void checkParam(){
        if(amount==null){
            throw new ValidException(UmsResCodeEnum.SYS_PARAM_NOT_NULL.getCode(),UmsResCodeEnum.SYS_PARAM_NOT_NULL.getMsg("商品编码不能为空"));
        }
        if(userId==null){
            throw new ValidException(UmsResCodeEnum.SYS_PARAM_NOT_NULL.getCode(),UmsResCodeEnum.SYS_PARAM_NOT_NULL.getMsg());
        }
    }
}
