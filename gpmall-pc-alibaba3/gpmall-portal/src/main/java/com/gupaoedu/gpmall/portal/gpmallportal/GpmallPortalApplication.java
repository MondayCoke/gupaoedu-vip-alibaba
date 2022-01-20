package com.gupaoedu.gpmall.portal.gpmallportal;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GpmallPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpmallPortalApplication.class, args);
    }



}
