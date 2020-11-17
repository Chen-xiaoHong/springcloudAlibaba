package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.PayMent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public Integer create(PayMent payMent);

    public PayMent queryPaymentById(@Param("id")Long id);
}
