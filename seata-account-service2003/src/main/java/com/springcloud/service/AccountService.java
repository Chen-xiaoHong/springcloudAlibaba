package com.springcloud.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface AccountService {
    void updateBalance(@RequestParam("userId")Long userId,@RequestParam("money") BigDecimal money);
}
