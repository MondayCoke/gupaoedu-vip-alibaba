package com.gupaoedu.gpmall.portal.gpmallalibabaportal.controller;

import com.gupaoedu.gpmall.user.IHelloService;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public class MockSayHelloService implements IHelloService {
    @Override
    public String sayHello() {
        return "sorry, 服务调用异常，被降级了";
    }
}
