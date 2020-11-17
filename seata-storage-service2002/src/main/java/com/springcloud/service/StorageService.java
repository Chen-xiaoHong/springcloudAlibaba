package com.springcloud.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface StorageService {
    void updateStock(@RequestParam("productId")Long productId, @RequestParam("count")Integer count);
}
