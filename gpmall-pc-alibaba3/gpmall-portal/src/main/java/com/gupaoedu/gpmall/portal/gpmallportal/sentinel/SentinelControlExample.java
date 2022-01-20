package com.gupaoedu.gpmall.portal.gpmallportal.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.Rule;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
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
public class SentinelControlExample {

    public static void main(String[] args) {
        flowRule();

        for (int i = 0; i < 10 ; i++) {
            ContextUtil.enter("context","ServiceB"); //设置上下文的请求调用来源
            try(Entry entry= SphU.entry("Hello")){
                System.out.println("访问成功");
            }catch (BlockException e){
                System.out.println("被限流了");
            }
        }
    }
    private static void flowRule(){
        List<FlowRule> rules=new ArrayList<>();
        FlowRule rule=new FlowRule();
        rule.setResource("Hello"); //针对那个资源设置规则
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的纬度
        rule.setCount(3); //设置qps=5
        rule.setStrategy(RuleConstant.STRATEGY_DIRECT);
        rule.setLimitApp("ServiceA");
        rules.add(rule);

        FlowRule rule1=new FlowRule();
        rule1.setResource("Hello"); //针对那个资源设置规则
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的纬度
        rule1.setCount(6); //设置qps=5
        rule1.setStrategy(RuleConstant.STRATEGY_DIRECT);
        rule1.setLimitApp("other");

        rule1.setStrategy(RuleConstant.STRATEGY_RELATE);
        rule1.setRefResource("");

        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }
}
