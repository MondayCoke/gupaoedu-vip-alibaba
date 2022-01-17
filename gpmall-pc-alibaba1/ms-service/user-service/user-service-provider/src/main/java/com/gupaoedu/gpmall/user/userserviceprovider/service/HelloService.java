package com.gupaoedu.gpmall.user.userserviceprovider.service;

import com.gupaoedu.gpmall.user.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@DubboService(loadbalance = "random")
public class HelloService implements IHelloService {

    @Override
    public String say(String msg) {
        return "Hello,"+msg+", I' Dubbo Service";
    }
}
