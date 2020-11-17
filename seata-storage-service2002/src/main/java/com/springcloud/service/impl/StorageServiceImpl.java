package com.springcloud.service.impl;

import com.springcloud.dao.StorageDao;
import com.springcloud.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void updateStock(Long productId, Integer count) {
        storageDao.updateStock(productId,count);
    }
}
