package com.gupaoedu.gpmall.user.userserviceprovider;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DefaultDubboFallback;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
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

    private final String remoteAddress="192.168.8.133"; //Nacos 远程服务host
    private final String groupId="SENTINEL_GROUP"; //Nacos GroupID
    private final String dataId="com.gupaoedu.gpmall.user.HelloService";

    @Override
    public void init() throws Exception {
            ReadableDataSource<String,List<FlowRule>> flowRuleDataSource=
                    new NacosDataSource<>(remoteAddress,groupId,dataId,
                            source-> JSON.parseObject(source,new TypeReference<List<FlowRule>>(){}));
            FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }
}
