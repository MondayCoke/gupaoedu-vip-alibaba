package com.gupaoedu.gpmall.marking.markingserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MarkingServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarkingServiceProviderApplication.class, args);
    }

}
