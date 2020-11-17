package com.atguigu.mysel;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class MySelfRule {

 /*   @Resource
    private static DiscoveryClient discoveryClient;

    public static String rotationRule(String PAYMENT_URL,Integer number){
        List<ServiceInstance> instances = discoveryClient.getInstances(PAYMENT_URL);
        int num = number % instances.size();
        return PAYMENT_URL + ":" + instances.get(num).getPort();
    }*/

    @Bean
    public IRule myRule(){
        return new RandomRule();
    }

}
