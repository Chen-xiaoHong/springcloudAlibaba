package com.springcloud.customerHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CustomerFallback {

    public static CommonResult fallback1(BlockException e){
        return new CommonResult(404,"沙口" + "*******");
    }

    public static CommonResult fallback2(BlockException e){
        return new CommonResult(400,"爱请时是什么东西" );
    }
}
