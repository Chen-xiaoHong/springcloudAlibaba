package com.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class StorageController {

    @Resource
    private StorageService storageService;

    @GetMapping("storage/updateStock")
    public CommonResult updateStock(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        log.info("开始减少库存");
        storageService.updateStock(productId,count);
        log.info("减少库存成功");
        return new CommonResult(200,"减少库存成功");
    }
}
