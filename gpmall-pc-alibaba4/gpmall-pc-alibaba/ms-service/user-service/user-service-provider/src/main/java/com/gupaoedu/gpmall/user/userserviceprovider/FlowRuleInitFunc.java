package com.gupaoedu.gpmall.user.userserviceprovider;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
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

    private static final String CLUSTER_SERVER_HOST="localhost";
    private static final int CLUSTER_SERVER_PORT=7777;
    private static int REQUEST_TIME_OUT=200000;

    private final String remoteAddress="192.168.8.133:8848";
    private final String groupId="SENTINEL_GROUP";
//    private final String FLOW_POSTFIX="-flow-rules";

    private final String APP_NAME="user-service-provider-flow-rules";

    @Override
    public void init() throws Exception {
        ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT); //声明当前是token-client

        loadClusterClientConfig();
        registerClusterFlowRule();
    }

    private void loadClusterClientConfig(){
        ClusterClientAssignConfig assignConfig=new ClusterClientAssignConfig();

        assignConfig.setServerHost(CLUSTER_SERVER_HOST);
        assignConfig.setServerPort(CLUSTER_SERVER_PORT);

        ClusterClientConfigManager.applyNewAssignConfig(assignConfig);

        ClusterClientConfig clientConfig=new ClusterClientConfig();
        clientConfig.setRequestTimeout(REQUEST_TIME_OUT);

        ClusterClientConfigManager.applyNewConfig(clientConfig);
    }

    private void registerClusterFlowRule(){
        ReadableDataSource<String,List<FlowRule>> dataSource=
                new NacosDataSource<List<FlowRule>>(remoteAddress,groupId,APP_NAME,
                        source->JSON.parseObject(source,new TypeReference<List<FlowRule>>(){}));
        FlowRuleManager.register2Property(dataSource.getProperty());
    }
}
