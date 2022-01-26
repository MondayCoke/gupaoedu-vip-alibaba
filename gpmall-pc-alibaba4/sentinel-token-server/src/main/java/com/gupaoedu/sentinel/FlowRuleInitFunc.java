package com.gupaoedu.sentinel;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class FlowRuleInitFunc implements InitFunc{
    private final String nacosAddress="192.168.8.133:8848";
    private final String groupId="SENTINEL_GROUP";
    private String dataId="-flow-rules";

    @Override
    public void init() throws Exception {
        ClusterFlowRuleManager.setPropertySupplier(namespace->{
            ReadableDataSource<String,List<FlowRule>> flowRuleDs=
                    new NacosDataSource<>(nacosAddress, groupId, namespace + dataId,
                            source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                            }));
            return flowRuleDs.getProperty();
        });
    }
}
