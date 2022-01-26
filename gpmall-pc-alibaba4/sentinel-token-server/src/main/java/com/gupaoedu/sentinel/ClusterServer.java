package com.gupaoedu.sentinel;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

import java.util.Collections;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ClusterServer {

    public static void main(String[] args) throws Exception {
        ClusterTokenServer tokenServer=new SentinelDefaultTokenServer();
        //手动载入namespace和serverTransportConfig的配置到ClusterServerConfigManager
        //集群限流服务端通信相关配置
        ClusterServerConfigManager.loadGlobalTransportConfig(
                new ServerTransportConfig().setIdleSeconds(600).setPort(7777));
        //加载namespace集合列表() ， namespace也可以放在配置中心user-service-provider-flow-rules
        ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("user-service-provider"));
        tokenServer.start();
        //Token-client会上报自己的project.name到token-server。Token-server会根据namespace来统计连接数
    }
}
