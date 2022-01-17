package com.gupaoedu.gpmall.portal.gpmallportal.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public class ExceptionUtils {

    public static String handlerTest01Exception(BlockException e){
        System.out.println("Occur BlockedException:"+e);
        return e.getMessage();
    }
}
