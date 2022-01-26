package com.gupaoedu.gpmall.portal.gpmallportal.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreaker;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.EventObserverRegistry;
import com.alibaba.csp.sentinel.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public class DegradeExample {
    private static final String RESOURCE="hello";

    public static void main(String[] args) {
        initDegradeRule();

        for (int i = 0; i < 2000; i++) {
            try(Entry entry= SphU.entry(RESOURCE)){
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10,100));
                System.out.println("current:"+i+" request success");
            }catch (BlockException e){
                System.out.println("current:"+i+", Occur BlockException: "+e.getMessage()+"");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void initDegradeRule(){
        List<DegradeRule> rules=new ArrayList<>();
        DegradeRule rule=new DegradeRule(RESOURCE)
                .setGrade(CircuitBreakerStrategy.SLOW_REQUEST_RATIO.getType())
                .setCount(20)
                .setTimeWindow(5)
                .setSlowRatioThreshold(0.2)
                .setMinRequestAmount(10)
                .setStatIntervalMs(1000); //ms
        rules.add(rule);

        DegradeRuleManager.loadRules(rules);

        EventObserverRegistry.getInstance().addStateChangeObserver("logging",(prevState, newState, rule1, snapshotValue) -> {
            if(newState== CircuitBreaker.State.OPEN){
                System.err.println(prevState.name()+ " 打开了熔断状态， currentTime: "+ TimeUtil.currentTimeMillis() +",  snapshotValue:"+snapshotValue);
            }else{
                System.err.println("prevState:"+prevState.name()+" ,  newState: "+newState.name()+" , curretTime:"+TimeUtil.currentTimeMillis());
            }
        });
    }
}
