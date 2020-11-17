package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class ProviderService {

    public String timeOut(){
        int time = 3;
        try{
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + "timeout\t时间为" + time + "极速飙车";
    }

    public String ok(){
        return Thread.currentThread().getName() + "ok\t" + "哈哈哈哈哈哈哈哈哈哈哈傻逼";
    }


    //服务熔断
    @HystrixCommand(fallbackMethod = "fuseFallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "1000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
    })
    public String paymentCircuitBreaker( Long id){
        if (id < 0) {
            throw new RuntimeException("*****id 不能负数");
        }
        String uuid = IdUtil.simpleUUID();
        return  "/t调用成功，流水号为：" + uuid;
    }

    public String fuseFallBack(Long id){
         return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }
}
