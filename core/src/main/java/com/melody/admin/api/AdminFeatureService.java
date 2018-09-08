package com.melody.admin.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.melody.product.dto.Category;
import com.melody.product.dto.Feature;
import com.melody.product.dto.FeatureOption;

import java.util.List;

public interface AdminFeatureService {

    int addFeature(Feature feature);

    Page<Feature> queryFeatureList(Page page);

    int addFeatureOption(FeatureOption featureOption);

    Page<FeatureOption> queryFeatureOptionList(Page page);

    List<Feature> queryFeatureByCategoryCode(String categoryCode);

    int deleteById(long featureId);
    int deleteOptionById(long featureId);

//    private Integer id;
//    private Integer featureId;
//    private String featureValue;  // 这个值可能是整数类型
}
