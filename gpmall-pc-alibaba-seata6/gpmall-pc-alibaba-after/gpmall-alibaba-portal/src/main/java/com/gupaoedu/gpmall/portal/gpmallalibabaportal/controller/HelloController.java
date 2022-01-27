package com.gupaoedu.gpmall.portal.gpmallalibabaportal.controller;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.gupaoedu.gpmall.user.IHelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@RestController
public class HelloController {

    @DubboReference
    IHelloService helloService;

    @GetMapping("/say")
    public String say(){
        return helloService.sayHello();
    }
}
