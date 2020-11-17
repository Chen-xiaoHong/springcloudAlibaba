package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.PayMent;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public Integer create(PayMent payMent);

    public PayMent queryPaymentById(@Param("id")Long id);
}
