package com.gupaoedu.gpmall.marking.dto;

import com.gupaoedu.gpmall.core.AbstractRequest;
import com.gupaoedu.gpmall.exception.ValidException;
import com.gupaoedu.gpmall.marking.enums.MmsResCodeEnum;
import lombok.Data;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 * 查询抽奖次数
 **/
@Data
public class UserDrawChanceRequest extends AbstractRequest {

    private Integer uid; //uid

    public void checkParam(){
        if(uid==null){
            throw new ValidException(MmsResCodeEnum.SYS_PARAM_NOT_NULL.getCode(),MmsResCodeEnum.SYS_PARAM_NOT_NULL.getMsg());
        }
    }
}
