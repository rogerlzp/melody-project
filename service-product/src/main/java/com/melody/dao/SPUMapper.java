package com.melody.dao;


import com.melody.product.dto.SPU;

public interface SPUMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SPU record);

    int insertSelective(SPU record);

    SPU selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SPU record);

    int updateByPrimaryKey(SPU record);

    SPU selectBySpuCode(String spuCode);

}