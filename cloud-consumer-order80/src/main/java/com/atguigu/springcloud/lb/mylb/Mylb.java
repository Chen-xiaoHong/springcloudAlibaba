package com.atguigu.springcloud.lb.mylb;

import com.atguigu.springcloud.lb.LoadBalance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Mylb implements LoadBalance {

    private  AtomicInteger atomicInteger = new AtomicInteger(0);

    public final  int getAndIncrement() {
        int current;
        int next;
        do {
         current = atomicInteger.get();
         next = current > Integer.MAX_VALUE ? 0 : current + 1;
        }while (!atomicInteger.compareAndSet(current,next));
        System.out.println("******************服务第"+ next + "次访问");
        return next;
    }

    @Override
    public ServiceInstance getLoadBanacle(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        System.out.println(index);
        return serviceInstances.get(index);
    }
}
