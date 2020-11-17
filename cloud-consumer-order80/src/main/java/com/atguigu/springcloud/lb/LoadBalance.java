package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalance {
    public ServiceInstance getLoadBanacle(List<ServiceInstance> serviceInstances);
}
