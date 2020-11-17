package com.atguigu.springcloud.service;

import org.springframework.messaging.Message;

public interface StreamConsumerService {
    public void getMessage(Message<String> message);
}
