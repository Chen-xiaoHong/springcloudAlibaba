package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.ProviderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProviderController {

    @Resource
    private ProviderService providerService;

    @GetMapping("/payment/ok")
    public String ok() {
        String ok = providerService.ok();
        return ok;
    }

    //服务降级
    @HystrixCommand(fallbackMethod = "fallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    @GetMapping("/payment/timeout")
    public String timeOut() {
        String ok = providerService.timeOut();
        return ok;
    }
    public String fallback(){
        return "系统繁忙，请十秒后再尝试" + "😁";
    }

    //服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id){
        String result = providerService.paymentCircuitBreaker(id);
        return result;
    }
}
