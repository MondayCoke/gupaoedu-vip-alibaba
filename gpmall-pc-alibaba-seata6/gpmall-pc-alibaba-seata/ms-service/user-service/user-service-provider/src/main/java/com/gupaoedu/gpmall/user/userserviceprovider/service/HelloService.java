package com.gupaoedu.gpmall.user.userserviceprovider.service;

import com.gupaoedu.gpmall.user.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Slf4j
@DubboService(cluster = "failfast",timeout = 1000)
public class HelloService implements IHelloService {

    @Override
    public String sayHello() {
        log.info("begin execute sayHello()");
        return "Hello,Apache Dubbo";
    }
}
