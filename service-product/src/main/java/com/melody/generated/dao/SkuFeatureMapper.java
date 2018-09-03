package com.melody.generated.dao;

import com.melody.generated.model.SkuFeature;

public interface SkuFeatureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuFeature record);

    int insertSelective(SkuFeature record);

    SkuFeature selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkuFeature record);

    int updateByPrimaryKey(SkuFeature record);
}