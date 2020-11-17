package com.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {
    //更新商品库存
    void updateStock(@Param("productId")Long productId, @Param("count")Integer count);
}
