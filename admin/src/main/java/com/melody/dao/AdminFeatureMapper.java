package com.melody.dao;

import com.melody.product.dto.Category;
import com.melody.product.dto.Feature;
import com.melody.product.dto.FeatureOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminFeatureMapper {


    int insert(Feature feature);

    int insertFeatureOption(FeatureOption featureOption);

    List<Feature> queryFeatureList(@Param(value = "offset") Integer offset,
                                   @Param(value = "pageSize") Integer pageSize);

    List<Feature> queryFeatureListByCategory(@Param(value = "categoryCode") String categoryCode);

    int countAllFeature();

    List<FeatureOption> queryFeatureOption(@Param(value = "featureId") Integer featureId);

    int deleteById(@Param(value = "id") Long id);
    int deleteOptionById(@Param(value = "id") Long id);

}
