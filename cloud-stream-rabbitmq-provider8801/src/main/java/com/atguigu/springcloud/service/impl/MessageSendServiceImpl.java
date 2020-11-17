package com.atguigu.springcloud.service.impl;


import com.atguigu.springcloud.service.MessageSendService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)
public class MessageSendServiceImpl implements MessageSendService {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        System.out.println("serial:***********" + serial);
        output.send(MessageBuilder.withPayload(serial).build());
        return serial;
    }
}
