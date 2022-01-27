package com.gupaoedu.gpmall.core;

import com.gupaoedu.gpmall.enums.CommonRetCodeEnums;
import lombok.Data;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Data
public class CommonResponse<T> {

    private int code;
    private String message;
    private boolean success;
    private T data;

    public static <T> CommonResponse<T> error(AbstractResponse response){
        return error(CommonRetCodeEnums.FAILED.getCode(),response.getMsg());
    }

    public static <T> CommonResponse<T> error(int code,String msg){
        CommonResponse<T> response=new CommonResponse<>();
        response.setCode(code);
        response.setMessage(msg);
        response.setSuccess(false);
        return response;
    }

    public static <T> CommonResponse<T> success(T data){
        CommonResponse<T> response=new CommonResponse<>();
        response.setCode(CommonRetCodeEnums.SUCCESS.getCode());
        response.setMessage(CommonRetCodeEnums.SUCCESS.getMsg());
        response.setData(data);
        response.setSuccess(true);
        return response;
    }
}
