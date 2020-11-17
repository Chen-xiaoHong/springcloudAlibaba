package com.atguigu.springcloud.service.fallback;

import com.atguigu.springcloud.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallBack implements OrderService {
    @Override
    public String ok() {
        return "ok 调用失败 他sl 🤐，请你不要再试了，我不行给你关闭熔断";
    }

    @Override
    public String timeOut() {
        return "timeOut 调用失败，请你不要再试了，我不行给你关闭熔断";
    }
}
