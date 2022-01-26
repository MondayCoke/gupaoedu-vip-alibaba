package com.gupaoedu.gpmall.portal.gpmallportal.dubbo;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gupaoedu.gpmall.user.IHelloService;
import jdk.nashorn.internal.ir.Block;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@RestController
@RequestMapping("/dubbo/sentinel")
public class DubboSentinelController {

    @DubboReference(check = false)
    IHelloService helloService;

    @GetMapping("/{name}")
    public String say(@PathVariable("name")String name){
        try {
            return helloService.say(name);
        }catch (Exception e){
            if(e instanceof BlockException) {
                return "被限流了";
            }
            return null;
        }
    }
}
