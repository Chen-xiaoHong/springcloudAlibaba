package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
//@DefaultProperties(defaultFallback = "globalFallBack")
public class OrderHystrixController {

    @Resource
    private OrderService orderService;

    @GetMapping("/consumer/payment/ok")
    public String ok(){
       return orderService.ok();
    }

   /* @GetMapping("/consumer/payment/timeout")
    public String timeOut(){
        return orderService.timeOut();
    }*/

   @GetMapping("/consumer/payment/timeout")
  /* @HystrixCommand(fallbackMethod = "partialFallBack",commandProperties = {
           @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
   })*/
 // @HystrixCommand
   public String timeOut() {
       String ok = orderService.timeOut();
       return ok;
   }
    public String partialFallBack(){
        return "系统繁忙，请十秒后再尝试" + "😭";
    }

    public String globalFallBack(){
        return "史达凯繁忙，请十秒后再尝试" + "😭";
    }
}
