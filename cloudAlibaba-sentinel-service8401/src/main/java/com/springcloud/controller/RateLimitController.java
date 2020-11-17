package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import com.springcloud.customerHandler.CustomerFallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/url1")
    @SentinelResource(value = "url1", blockHandler = "detail_url1")
    public CommonResult testA() {
        return new CommonResult(200, "按照资源名称限流测试", new PayMent(2020l, "url1"));
    }

    public CommonResult detail_url1(BlockException e) {
        return new CommonResult(404, e.getClass() + "找不到对应的资源路径");
    }


    @GetMapping("/customerFallBack")
    @SentinelResource(value = "customerFallback",
            blockHandlerClass = CustomerFallback.class,
            blockHandler = "fallback2")
    public CommonResult testCustomerFallBack() {
        return new CommonResult(200, "你可真是个嘤嘤怪");
    }


}
