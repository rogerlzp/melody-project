package com.melody.dao;


import com.melody.product.dto.Feature;
import com.melody.product.dto.FeatureOption;
import com.melody.product.dto.SPU;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SPUMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SPU record);

    int insertSelective(SPU record);

    SPU selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SPU record);

    int updateByPrimaryKey(SPU record);

    SPU selectBySpuCode(String spuCode);

    List<Feature> getFeatureListBySpuCode(@Param(value = "spuCode") String spuCode);

    List<FeatureOption> getFeatureOptionByFeationId(@Param(value = "featureId") Integer featureId);
}