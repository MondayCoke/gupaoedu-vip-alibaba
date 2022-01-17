package com.gupaoedu.gpmall.portal.gpmallportal.sentinel;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/sentinel")
public class SentinelAnnotationController {

     //@SentinelResource

    @Autowired
    SentinelAnnotationService sentinelAnnotationService;

    @GetMapping("/test01")
    public String test01(){
        return sentinelAnnotationService.test01();
    }

    @GetMapping("/test02")
    public String test02(){
        return sentinelAnnotationService.test02();
    }

}
