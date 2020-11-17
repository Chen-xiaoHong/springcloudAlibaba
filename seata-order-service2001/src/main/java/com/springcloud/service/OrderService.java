package com.springcloud.service;

import com.springcloud.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    //新建订单
    void createOrder(Order order);
}
