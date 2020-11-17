package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import com.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service.url}")
    private String serviceUrl;

    /**
     * 当sentinelResource中两个注解都配置，两个违法操作都触发时候，会执行限流的兜底方法
     * fallback ：用来处理java异常
     * blockHandler： 用来处理访问限流的
     * @param id
     * @return
     */
    @GetMapping("/order/{id}")
    @SentinelResource(value = "fallback",fallback = "paymentFallback",
            blockHandler = "paymentBlockHandler",exceptionsToIgnore = IllegalArgumentException.class)
    public CommonResult<PayMent> getPayment(@PathVariable("id") long id) {

        if (id == 2004) {
            throw new IllegalArgumentException(id + "IllegalArgumentException 非法参数异常");
        }else if (id > 2004 ) {
            throw new NullPointerException(id + "空指针异常....");
        }
        CommonResult result = restTemplate.getForObject(serviceUrl + "/payment/" + id, CommonResult.class);
        return result;
    }

    public CommonResult paymentFallback(@PathVariable long id,Throwable e){
        PayMent payMent = new PayMent(id, null);
        return new CommonResult(444,id + "fallback发生的异常为：" + e.getMessage(),payMent);
    }

    public CommonResult paymentBlockHandler(@PathVariable long id, BlockException e){
        PayMent payMent = new PayMent(id, null);
        return new CommonResult(455,"blockHandler发生的异常为：" + e.getMessage(),payMent);
    }

    /**
     * **************************************************
     */
    @Resource
    private PaymentService paymentService;

    @GetMapping("feign/order/{id}")
    public CommonResult<PayMent> getFeignPayment(@PathVariable("id") long id) {
        CommonResult result = paymentService.payment(id);
        return result;
    }
}
