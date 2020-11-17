package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StreamRqbbitMqConsumer8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamRqbbitMqConsumer8802.class,args);
    }
}
