package com.gupaoedu.gpmall.portal.gpmallportal.sentinel;

import com.gupaoedu.gpmall.user.ICircuitService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@RestController
@RequestMapping("/degrade")
public class SentinelDegradeController {

    @DubboReference(mock = "com.gupaoedu.gpmall.portal.gpmallportal.sentinel.CircuitServiceMock")
    ICircuitService circuitService;


    @GetMapping("/say")
    public String say(){
        return circuitService.sayHello("Mic");
    }

    @GetMapping("/exception")
    public void exception(){
        circuitService.exception(true);
    }
}
