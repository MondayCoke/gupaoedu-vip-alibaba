package com.gupaoedu.gpmall.portal.gpmallportal.limit;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public class SemaphoreExample {

    public static void main(String[] args) {

        ExecutorService exec= Executors.newCachedThreadPool();
        Semaphore semaphore=new Semaphore(5); //

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            final Random random=new Random();
            Runnable run=new Runnable() {
                @Override
                public void run() {
                    try {
                        if(semaphore.tryAcquire()){
                            System.out.println("Access:"+ finalI);

                            Thread.sleep(random.nextInt(1000));
                            System.out.println("释放令牌");
                            semaphore.release(); //释放token
                        }else{
                            System.out.println("Failed");
                        }
//                        semaphore.acquire(); //获得token
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }

        exec.shutdown();
    }

}
