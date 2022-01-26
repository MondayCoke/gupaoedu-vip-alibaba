package com.gupaoedu.gpmall.portal.gpmallportal.sentinel;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
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
public class DegradeRuleInitFunc implements InitFunc {


    @Override
    public void init() throws Exception {
        loadDegradeRule();
        registerFallback();
    }

    private void registerFallback(){
        DubboAdapterGlobalConfig.setConsumerFallback(new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException e) {
                AsyncRpcResult arr=new AsyncRpcResult(null,invocation);
                arr.setException(e.getCause());
                return arr;
            }
        });
    }

    private void loadDegradeRule(){
        List<DegradeRule> rules=new ArrayList<>();
        DegradeRule rule=new DegradeRule("com.gupaoedu.gpmall.user.ICircuitService")
                .setGrade(CircuitBreakerStrategy.ERROR_COUNT.getType())
                .setCount(3)
                .setTimeWindow(10);
        rules.add(rule);

        DegradeRuleManager.loadRules(rules);
    }
}
