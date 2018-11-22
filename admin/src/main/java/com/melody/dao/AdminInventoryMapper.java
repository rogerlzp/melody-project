package com.melody.dao;

public interface AdminInventoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuInventory record);

    int insertSelective(SkuInventory record);

    SkuInventory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkuInventory record);

    int updateByPrimaryKey(SkuInventory record);
}