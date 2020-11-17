package com.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.PayMent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    public static HashMap<Long, PayMent> hashMap = new HashMap();

    static {
        hashMap.put(2001l, new PayMent(2001l, "TES"));
        hashMap.put(2002l, new PayMent(2002l, "JDG"));
        hashMap.put(2003l, new PayMent(2003l, "SN"));
    }

    @Value("${server.port}")
    private long port;

    @GetMapping("/payment/{id}")
    public CommonResult payment(@PathVariable("id") long id) {
        PayMent payMent = hashMap.get(id);
        return new CommonResult(200, "从数据库获得" + port + payMent.toString(),payMent);
    }
}
