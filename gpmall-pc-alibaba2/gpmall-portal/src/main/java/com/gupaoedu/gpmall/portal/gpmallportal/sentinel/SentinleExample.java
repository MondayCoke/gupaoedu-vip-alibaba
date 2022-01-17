package com.gupaoedu.gpmall.portal.gpmallportal.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
public class SentinleExample {

    //资源
    private final static String RESOURCE="SentinleExample";
    static {
        flowRule();
    }
    public static void main(String[] args) {
        Random random=new Random();
        while(true){
            //获取一个令牌（自动释放资源（令牌））
            try(Entry entry= SphU.entry(RESOURCE)) {
                System.out.println("Request Success");
//                Thread.sleep(random.nextInt(300));
//                entry.exit();
            }catch (BlockException e){
                System.out.println("Request Blocked");
            }
            if(SphO.entry(RESOURCE)){
                try{
                    System.out.println("Request Success");
                }finally {
                    SphO.exit(); //释放令牌
                }
            }else{
                System.out.println("Request Blocked");
            }
        }
    }

    private static void flowRule(){
        List<FlowRule> rules=new ArrayList<>();
        FlowRule rule=new FlowRule();
        rule.setResource(RESOURCE); //针对那个资源设置规则
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的纬度
        rule.setCount(5); //设置qps=5
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
