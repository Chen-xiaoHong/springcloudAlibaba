package com.atguigu.springcloud.conrtoller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import com.atguigu.springcloud.service.OrderFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderOpenFeignController {

    @Resource
    private OrderFeignService  orderFeignService;

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<PayMent> get(@PathVariable("id") Long id){
        return orderFeignService.queryPaymentById(id);
    }

    @GetMapping("consumer/payment/get/timeout/{id}")
    public CommonResult<PayMent> timeout(@PathVariable("id") Long id){
        return orderFeignService.timeout(id);
    }
}
