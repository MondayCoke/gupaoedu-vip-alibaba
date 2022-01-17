package com.gupaoedu.example.nacosdemo;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@RestController
public class NacosController {

    @NacosInjected
    private NamingService namingService;


    /**
     * 服务发现
     * @param serviceName
     * @return
     * @throws NacosException
     */
    @GetMapping("/discovery/{name}")
    public List<Instance> get(@PathVariable("name") String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    /**
     * 服务注册
     * @throws NacosException
     */
    @GetMapping("/registry")
    public void registry() throws NacosException {
        Instance instance=new Instance();
        instance.setClusterName("test1");
        instance.setEnabled(true);
        instance.setEphemeral(true);
        instance.setHealthy(true);
        instance.setIp("10.1.110.27");
        instance.setPort(8080);
        namingService.registerInstance("example",instance);
    }
}
