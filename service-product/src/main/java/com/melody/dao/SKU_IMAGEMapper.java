package com.melody.dao;

import com.melody.generated.model.SKU_IMAGE;

public interface SKU_IMAGEMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SKU_IMAGE record);

    int insertSelective(SKU_IMAGE record);

    SKU_IMAGE selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SKU_IMAGE record);

    int updateByPrimaryKey(SKU_IMAGE record);
}