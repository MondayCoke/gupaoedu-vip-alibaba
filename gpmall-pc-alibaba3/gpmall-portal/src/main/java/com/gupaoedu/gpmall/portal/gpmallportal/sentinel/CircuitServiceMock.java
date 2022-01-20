package com.gupaoedu.gpmall.portal.gpmallportal.sentinel;

import com.gupaoedu.gpmall.user.ICircuitService;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public class CircuitServiceMock implements ICircuitService {
    @Override
    public String sayHello(String msg) {
        return "触发了熔断降级： sayHello"+msg;
    }

    @Override
    public String exception(boolean biz) {
        return "触发熔断降级， exception";
    }
}
