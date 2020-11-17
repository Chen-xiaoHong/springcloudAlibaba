package com.atguigu.springcloud.controller;

import com.atguigu.mysel.MySelfRule;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import com.atguigu.springcloud.lb.mylb.Mylb;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderController {

//    private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://cloud-payment-service/";

    @Resource
    private Mylb mylb = new Mylb();

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<PayMent> create(PayMent payMent){
            return restTemplate.postForObject(PAYMENT_URL + "/payment/create/", payMent, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<PayMent> get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<PayMent> getEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is4xxClientError()) {
            return new CommonResult<>(444,"您访问的资源出问题了！");
        }
        if (entity.getStatusCode().is2xxSuccessful()) {
            return new CommonResult<PayMent>(entity.getStatusCodeValue(),entity.getHeaders() + "/t" + entity.getBody());
        }
        return entity.getBody();
    }

    @GetMapping("/consumer/payment/create/postForEntity")
    public CommonResult<PayMent> create2(PayMent payMent){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payMent, CommonResult.class);
        if (entity.getStatusCode().is5xxServerError()) {
            return new CommonResult<>(500,"插入数据失败");
        }
        return new CommonResult<>(200,"插入成功");
    }

    @GetMapping("/consumer/payment/lb")
    public Integer testLoadBalancer(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        ServiceInstance loadBanacle = mylb.getLoadBanacle(instances);
        return loadBanacle.getPort();
    }

    @GetMapping("/consumer/payment/zipkin")
    public String zipkin(){
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }
}
