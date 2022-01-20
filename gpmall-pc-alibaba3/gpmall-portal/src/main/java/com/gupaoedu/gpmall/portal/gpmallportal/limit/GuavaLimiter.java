package com.gupaoedu.gpmall.portal.gpmallportal.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public class GuavaLimiter {
        
    static RateLimiter rateLimiter=RateLimiter.create(10);  //qps=10

    //限制方法级别的并发访问量
    public void doRequest(String threadName){
        if(rateLimiter.tryAcquire()){ //true表示获取了一个令牌，false表示获取失败
            System.out.println("process success:"+threadName);
        }else{
            //TODO （降级  / 抛异常）
            System.out.println("process Failed:"+threadName);
        }
    }

    public static void main(String[] args) throws IOException {
        CountDownLatch latch=new CountDownLatch(1);
        Random random=new Random();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    latch.await();
                    GuavaLimiter guavaLimiter = new GuavaLimiter();
                    Thread.sleep(random.nextInt(1000));
                    guavaLimiter.doRequest("t-" + finalI);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
        latch.countDown();
        System.in.read();
    }

}
