package com.gupaoedu.gpmall.user.userserviceprovider;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public class FlowRuleInitFunc implements InitFunc {

    private final String remoteAddress="192.168.8.133:8848";
    private final String groupId="SENTINEL_GROUP";
    private final String dataId="com.gupaoedu.gpmall.user.IHelloService";

    @Override
    public void init() throws Exception {
        ReadableDataSource<String,List<FlowRule>> flowRuleDataSource=
                new NacosDataSource<>(remoteAddress, groupId, dataId, s -> JSON.parseObject(s, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

       /* List<FlowRule> rules=new ArrayList<>();
        FlowRule rule=new FlowRule();
        //com.alibaba.cloud.dubbo.service.DubboMetadataService:getServiceRestMetadata()
        rule.setResource("com.gupaoedu.gpmall.user.IHelloService:sayHello()"); //针对那个资源设置规则
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的纬度
        rule.setCount(2); //设置qps=5
        rules.add(rule);
        FlowRuleManager.loadRules(rules);*/
    }
}
