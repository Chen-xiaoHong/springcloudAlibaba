package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.MessageSendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StreamProviderController {

    @Resource
    private MessageSendService messageSendService;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return messageSendService.send();
    }
}
