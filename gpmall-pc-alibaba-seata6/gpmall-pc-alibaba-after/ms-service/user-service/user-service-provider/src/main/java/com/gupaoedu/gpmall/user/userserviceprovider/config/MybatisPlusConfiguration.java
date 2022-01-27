package com.gupaoedu.gpmall.user.userserviceprovider.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * https://ke.gupaoedu.cn
 **/
@Configuration
@MapperScan(basePackages = "com.gupaoedu.gpmall.user.userserviceprovider.dal.mapper")
@EnableTransactionManagement
public class MybatisPlusConfiguration {
}
