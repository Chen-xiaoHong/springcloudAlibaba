package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController()
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("payment/create")
    public CommonResult create(@RequestBody PayMent payMent){
        Integer result = paymentService.create(payMent);
        if (result > 0){
            return new CommonResult(200,"插入数据成功" + serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败" + serverPort,null);
        }
    }

    @GetMapping("payment/get/{id}")
    public CommonResult queryPaymentById(@PathVariable("id")Long id){
        PayMent payMent = paymentService.queryPaymentById(id);
        log.info("********查询结果" + payMent);
        if (payMent != null){
            return new CommonResult(200,"查询成功" + serverPort,payMent);
        }else {
            return new CommonResult(444,"没有对应记录，查询数据失败id" + id + ":" + serverPort,null);
        }
    }

    @GetMapping("consumer/payment/get/timeout/{id}")
    public CommonResult<PayMent> timeout(@PathVariable("id") Long id){
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PayMent payMent = paymentService.queryPaymentById(id);
        log.info("********查询结果" + payMent);
        if (payMent != null){
            return new CommonResult(200,"查询成功" + serverPort,payMent);
        }else {
            return new CommonResult(444,"没有对应记录，查询数据失败id" + id + ":" + serverPort,null);
        }
    }

    @GetMapping("payment/get/lb")
    public String lb(){
        return serverPort;
    }
}
