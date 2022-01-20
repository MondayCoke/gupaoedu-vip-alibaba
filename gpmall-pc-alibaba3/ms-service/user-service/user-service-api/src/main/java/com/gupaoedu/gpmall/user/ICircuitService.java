package com.gupaoedu.gpmall.user;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public interface ICircuitService {

    /**
     * 正常的方法
     * @param msg
     * @return
     */
    String sayHello(String msg);

    /**
     * 专门用来触发异常，从而实现熔断
     * @param biz
     * @return
     */
    String exception(boolean biz);
}
