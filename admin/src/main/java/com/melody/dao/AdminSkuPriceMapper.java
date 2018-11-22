package com.melody.dao;

public interface AdminSkuPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuPrice record);

    int insertSelective(SkuPrice record);

    SkuPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkuPrice record);

    int updateByPrimaryKey(SkuPrice record);
}