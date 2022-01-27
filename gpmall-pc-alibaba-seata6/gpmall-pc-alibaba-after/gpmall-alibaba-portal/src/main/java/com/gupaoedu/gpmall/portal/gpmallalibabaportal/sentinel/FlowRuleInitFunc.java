package com.gupaoedu.gpmall.portal.gpmallalibabaportal.sentinel;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;

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
        loadDegradeRule();
        registerFallback();
    }
    private void registerFallback(){
        DubboAdapterGlobalConfig.setConsumerFallback(new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException ex) {
                AsyncRpcResult arr=new AsyncRpcResult(null,invocation);
                arr.setException(ex.getCause());
                return arr;
            }
        });
    }
    private void loadDegradeRule(){
        List<DegradeRule> rules=new ArrayList<>();
        DegradeRule rule=new DegradeRule();
        //下面这个配置的意思是，当1s内持续进入5个请求，平均响应时间都超过count(10ms),
        // 那么在接下来的timewindow(10s)内，对
        //这个方法的调用都会自动熔断，抛出异常:degradeException.
        //指定被保护的资源
        rule.setResource("com.gupaoedu.gpmall.user.ICircuitService");
        rule.setCount(3); //阈值
        //降级模式, RT（平均响应时间）、异常比例()/异常数量
        rule.setGrade(CircuitBreakerStrategy.ERROR_COUNT.getType());
        rule.setTimeWindow(10);//降级的时间单位, 单位为s
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

    /*@Override
    public void init() throws Exception {
        List<FlowRule> rules=new ArrayList<>();
        DegradeRule dr=null;
        FlowRule rule=new FlowRule();
        rule.setResource("doTest");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setWarmUpPeriodSec(60);
        rule.setCount(1000);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }*/
}