package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Value("${server.port}")

   private String serverPort;

    @GetMapping("/payment/test/{id}")
    public String testConnection(@PathVariable Long id){
        return "payment端口：" + serverPort + "/t" + id;
    }
}
