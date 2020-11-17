package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-service")
public interface OrderFeignService {
    @GetMapping("payment/get/{id}")
    public CommonResult queryPaymentById(@PathVariable("id")Long id);
    @GetMapping("consumer/payment/get/timeout/{id}")
    public CommonResult<PayMent> timeout(@PathVariable("id") Long id);
}
