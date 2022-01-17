package com.gupaoedu.gpmall.portal.gpmallportal.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Service
public class SentinelAnnotationService {


    @SentinelResource(value = "test01",
            blockHandler = "handlerTest01Exception",
            blockHandlerClass = ExceptionUtils.class)

    public String test01(){
        System.out.println("Test01 Success");
        return "Test01 Success";
    }


    @SentinelResource(value = "test02",fallback = "test02Fallback")
    public String test02(){
        return "Hello,Mic";
    }

    public String test02Fallback(){
        return "Hello, I'm Fallback";
    }

}
