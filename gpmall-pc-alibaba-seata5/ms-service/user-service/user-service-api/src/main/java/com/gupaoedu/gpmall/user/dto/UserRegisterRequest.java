package com.gupaoedu.gpmall.user.dto;

import com.gupaoedu.gpmall.core.AbstractRequest;
import com.gupaoedu.gpmall.exception.ValidException;
import com.gupaoedu.gpmall.user.enums.UmsResCodeEnum;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Data
public class UserRegisterRequest extends AbstractRequest {
    private String username;
    private String password;

    public void checkParam(){
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            throw new ValidException(UmsResCodeEnum.SYS_PARAM_NOT_NULL.getCode(),UmsResCodeEnum.SYS_PARAM_NOT_NULL.getMsg());
        }
    }
}
