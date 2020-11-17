package com.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-provider-payment",fallback = PaymentFallback.class)
public interface PaymentService {
    @GetMapping("/payment/{id}")
    public CommonResult payment(@PathVariable("id") long id);
}
