package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.PayMent;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public Integer create(PayMent payMent) {
        return paymentDao.create(payMent);
    }

    @Override
    public PayMent queryPaymentById(Long id) {
        return paymentDao.queryPaymentById(id);
    }
}
