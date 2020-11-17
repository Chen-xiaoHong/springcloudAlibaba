package com.springcloud.service.impl;

import com.springcloud.dao.OrderDao;
import com.springcloud.domain.Order;
import com.springcloud.service.AccountService;
import com.springcloud.service.OrderService;
import com.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @Resource
    private OrderDao orderDao;

    @Override
    @GlobalTransactional(rollbackForClassName = {"Exception"})
    public void createOrder(Order order) {
        //先创建订单
        log.info(order.toString());
        log.info("---->开始创建订单");
        orderDao.createOrder(order);
        log.info("---->订单创建成功");
        //创建订单成功后，删减库存
        log.info("---->开始删减库存");
        storageService.updateStock(order.getProductId(), order.getCount());
        log.info("---->删减库存成功");
        //然后删减用户金额
        log.info("----->开始更新用户金额");
        accountService.updateBalance(order.getUserId(), order.getMoney());
        log.info("----->用户金额更新成功");
        //开始更新订单状态
        orderDao.updateOrderStatus(order.getUserId(),0);
        log.info("----->订单状态更新成功！");
    }


}
