package com.springcloud.service;

import com.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService {
    @GetMapping("/account/updateBalance")
    CommonResult updateBalance(@RequestParam("userId")Long userId,@RequestParam("money") BigDecimal money);
}
