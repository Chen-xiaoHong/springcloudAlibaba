package com.springcloud.dao;

import com.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    //新建订单
    void createOrder(Order order);

    //修改订单状态
    void updateOrderStatus(@Param("userId")Long userId,@Param("status")Integer status);
}
