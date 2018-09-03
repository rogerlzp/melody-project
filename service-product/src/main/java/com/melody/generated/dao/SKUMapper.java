package com.melody.generated.dao;

import com.melody.generated.model.SKU;

public interface SKUMapper {
    int deleteByPrimaryKey(String skuNo);

    int insert(SKU record);

    int insertSelective(SKU record);

    SKU selectByPrimaryKey(String skuNo);

    int updateByPrimaryKeySelective(SKU record);

    int updateByPrimaryKey(SKU record);
}