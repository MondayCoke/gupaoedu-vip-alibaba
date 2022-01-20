package com.gupaoedu.gpmall.user.userserviceprovider.service;

import com.gupaoedu.gpmall.user.ICircuitService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@DubboService(cluster = "failfast",timeout = 1000)
public class CircuitService implements ICircuitService {

    @Override
    public String sayHello(String msg) {
        return String.format("Hello, %s ",msg);
    }

    @Override
    public String exception(boolean biz) {
        if(biz){
            throw new RuntimeException("Biz Exception");
        }
        return "success";
    }
}
