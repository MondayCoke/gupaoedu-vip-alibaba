package com.gupaoedu.gpmall.enums;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public enum CommonRetCodeEnums {
    SUCCESS(200,"成功"),
    FAILED(500,"失败");

    private int code;
    private String msg;
    CommonRetCodeEnums(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
