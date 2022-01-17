package com.gupaoedu.gpmall.user.userserviceprovider;

import com.alibaba.csp.sentinel.init.InitFunc;
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
public class FlowRuleInitFunc implements InitFunc {

    @Override
    public void init() throws Exception {
        List<FlowRule> rules=new ArrayList<>();
        FlowRule rule=new FlowRule();
        //com.alibaba.cloud.dubbo.service.DubboMetadataService:getServiceRestMetadata()
        rule.setResource("com.gupaoedu.gpmall.user.IHelloService:sayHello()"); //针对那个资源设置规则
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的纬度
        rule.setCount(2); //设置qps=5
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
