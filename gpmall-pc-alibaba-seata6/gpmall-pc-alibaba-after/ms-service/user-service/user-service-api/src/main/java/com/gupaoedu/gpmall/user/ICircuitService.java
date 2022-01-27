package com.gupaoedu.gpmall.user;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public interface ICircuitService {

    /**
     * 正常的接口调用
     * @param msg
     * @return
     */
    String sayHello(String msg);

    /**
     * 通过异常触发熔断
     * @param biz
     * @return
     */
    String exception(boolean biz);
}
