package com.gupaoedu.gpmall.portal.gpmallalibabaportal.sentinel;

import com.gupaoedu.gpmall.user.ICircuitService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@RestController
public class SentinelController {
    @Autowired
    TestService testService;

    @GetMapping("/hi")
    public String doTest(){
        return testService.doTest();
    }

    @DubboReference(mock = "com.gupaoedu.gpmall.portal.gpmallalibabaportal.sentinel.CircuitServiceMock")
    ICircuitService circuitService;

    @GetMapping("/say1")
    public String say(){
        return circuitService.sayHello("Mic");
    }

    @GetMapping("/exception")
    public void exception(){
        circuitService.exception(true);
    }
}