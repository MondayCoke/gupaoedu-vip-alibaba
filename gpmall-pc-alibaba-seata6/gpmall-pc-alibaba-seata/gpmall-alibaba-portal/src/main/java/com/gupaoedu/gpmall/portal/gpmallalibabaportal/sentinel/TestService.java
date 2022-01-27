package com.gupaoedu.gpmall.portal.gpmallalibabaportal.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Service
public class TestService {

    @SentinelResource(value = "doTest",blockHandler = "handerException")
    public String doTest(){
        return "Hello,"+new Date();
    }

    public String handerException(BlockException e){
        return "被限流了";
    }
}
