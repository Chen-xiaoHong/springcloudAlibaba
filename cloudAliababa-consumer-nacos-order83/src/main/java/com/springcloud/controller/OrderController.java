package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Value("${service-url.nacos-user-service}")
    private String SERVER_URL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/test/{id}")
    public String test(@PathVariable("id") Long id) {
        System.out.println(SERVER_URL);
        return restTemplate.getForObject(SERVER_URL + "/payment/test/" + id,String.class);
    }
}
