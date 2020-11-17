package com.springcloud.service.impl;

import com.springcloud.dao.AccountDao;
import com.springcloud.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;


    @Override
    public void updateBalance(Long userId, BigDecimal money) {
        accountDao.updateBalance(userId,money);
    }
}
