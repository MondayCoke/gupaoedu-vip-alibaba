package com.gupaoedu.gpmall.portal.gpmallportal.sentinel;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public class FlowRuleInitFunc implements InitFunc {

    @Override
    public void init() throws Exception {
        loadFlowRule();
    }

    private static void loadFlowRule(){
      /*  DegradeRule dr=null;
        //异常比例的属性设置
        dr.setGrade(CircuitBreakerStrategy.ERROR_RATIO.getType());
        dr.setCount(0.6); //表示异常的比例  [0.0 , 1.0]
        dr.setTimeWindow(5); //时间窗口（熔断的时间）
        dr.setMinRequestAmount(5);
        //异常数量
        dr.setGrade(CircuitBreakerStrategy.ERROR_COUNT.getType());
        dr.setCount(10); //表示异常数量
        dr.setTimeWindow(5); //时间窗口（熔断的时间）
        dr.setMinRequestAmount(5);
        dr.setStatIntervalMs(60*1000);
        //慢调用比例
        dr.setGrade(CircuitBreakerStrategy.SLOW_REQUEST_RATIO.getType());
        dr.setCount(3000); //最大响应时间，单位是毫秒
        dr.setTimeWindow(5); //时间窗口（熔断的时间）
        dr.setMinRequestAmount(5);
        dr.setSlowRatioThreshold(0.2); //慢调用比例的阈值
        dr.setStatIntervalMs(60*1000);*/



        List<FlowRule> rules=new ArrayList<>();
        FlowRule rule=new FlowRule();
        rule.setResource("test01"); //针对那个资源设置规则
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的纬度
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER); //流控行为:warm-up
        rule.setCount(1000); //设置qps=5
        rule.setMaxQueueingTimeMs(3000);
//        rule.setWarmUpPeriodSec(60); //60s的预热时间

        //根据调用方限流
        /*rule.setLimitApp();
        rule.setStrategy(RuleConstant.STRATEGY_DIRECT);*/


        rules.add(rule);

        FlowRule rule1=new FlowRule();
        rule1.setResource("test02"); //针对那个资源设置规则
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的纬度
        rule1.setCount(2); //设置qps=5 , 每秒5个请求， 200ms通过一个.
        rules.add(rule1);

        FlowRuleManager.loadRules(rules);
    }
}
