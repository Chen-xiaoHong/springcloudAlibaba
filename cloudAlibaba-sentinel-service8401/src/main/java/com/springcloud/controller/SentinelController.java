package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class SentinelController {

    @GetMapping("/testA")
    public String getTestA(){
        try{
            TimeUnit.SECONDS.sleep(8);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "**************获取到testA";
    }

    @GetMapping("/testB")
    public String getTestB(){
        log.info(Thread.currentThread().getName() + "/t" + "testB");
        return "**************获取到testB";
    }

    @GetMapping("/lianLu/testC")
    public String getTestC(){
        return "**************获取到testC";
    }

    @GetMapping("/testD")
    public String getTestD(){
        try {
            TimeUnit.SECONDS.sleep(1);
            log.info("testD测试");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "**************获取到testD";
    }

    @GetMapping("/lianLu/testE")
    public String getTestE(){
        int i = 10/0;
        return "**************获取到testE";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "hotKey",blockHandler = "detail_hotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p1",required = false)String p2){
        return "*********testHotKey";
    }

    public String detail_hotKey(String p1, String p2, BlockException e){
        return "*********你个憨批，出错了啊！";
    }

    @GetMapping("/testCpu")
    public String testCpu(){
        int i = 0,j = 0;
        while (true){
            while (true){
                i = i++;
                if (i>1)
                    j++;
            }
        }
    }
}
