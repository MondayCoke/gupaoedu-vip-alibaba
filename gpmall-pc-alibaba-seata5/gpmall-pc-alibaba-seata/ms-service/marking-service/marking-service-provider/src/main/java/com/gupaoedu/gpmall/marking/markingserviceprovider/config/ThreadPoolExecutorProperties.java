package com.gupaoedu.gpmall.marking.markingserviceprovider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Data
@ConfigurationProperties(prefix = "async.executor.thread")
public class ThreadPoolExecutorProperties {
    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
    private String namePrefix;

}
