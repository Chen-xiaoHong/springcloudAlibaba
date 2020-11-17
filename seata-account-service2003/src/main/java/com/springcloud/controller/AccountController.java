package com.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/account/updateBalance")
    public CommonResult updateBalance(@RequestParam("userId")Long userId,@RequestParam("money") BigDecimal money){
      //开始更新用户余额
        log.info("更新用户金额");
        accountService.updateBalance(userId, money);
        log.info("用户金额更新完成");
        return new CommonResult(200,"用户金额已经更新完成");
    }
}
