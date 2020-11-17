package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.StreamConsumerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableBinding(Sink.class)
public class StreamConsumerServiceImpl implements StreamConsumerService {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void getMessage(Message<String> message) {
        System.out.println("端口号：" + serverPort + "\t消费者1号接受" + message.getPayload() );
    }
}
