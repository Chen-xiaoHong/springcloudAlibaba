package com.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallback implements PaymentService {
    @Override
    public CommonResult payment(long id) {
        return new CommonResult(4444,"爱你码卖麻花情",null);
    }
}
