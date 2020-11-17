package com.atguigu.springcloud.service;

import com.atguigu.springcloud.service.fallback.OrderServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "cloud-provider-hystrix-service",fallback = OrderServiceFallBack.class)
public interface OrderService {
    @GetMapping("/payment/ok")
    public String ok();

    @GetMapping("/payment/timeout")
    public String timeOut();
}
