package com.melody.generated.dao;

import com.melody.generated.model.SPU;

public interface SPUMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SPU record);

    int insertSelective(SPU record);

    SPU selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SPU record);

    int updateByPrimaryKey(SPU record);
}