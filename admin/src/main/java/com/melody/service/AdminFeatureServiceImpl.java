package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminCategoryService;
import com.melody.admin.api.AdminFeatureService;
import com.melody.dao.AdminCategoryMapper;
import com.melody.dao.AdminFeatureMapper;
import com.melody.product.dto.Category;
import com.melody.product.dto.Feature;
import com.melody.product.dto.FeatureOption;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(group = "adminFeatureService")
public class AdminFeatureServiceImpl implements AdminFeatureService {

    @Autowired
    AdminFeatureMapper adminFeatureMapper;

    @Autowired
    BaseServiceImpl baseService;

    @Override
    public int addFeature(Feature feature) {
        int featureId = baseService.getNextSequence("TT_FEATURE").intValue();
        feature.setId(featureId);

        int insertResult = adminFeatureMapper.insert(feature);
        // add feature option
        if (insertResult != 0) {
            for (FeatureOption featureOption : feature.getFeatureOptions()) {
                long featureOptionId = baseService.getNextSequence("TR_FEATURE_OPTION");
                featureOption.setId(featureOptionId);
                featureOption.setFeatureId(featureId);
                adminFeatureMapper.insertFeatureOption(featureOption);
            }
        }

        return insertResult;
    }

    @Override
    public Page<Feature> queryFeatureList(Page page) {
        int currentPage = page.getCurrent()-1; // current为1， 所以往后减一位
        int pageSize = page.getSize();
        int totalCount = adminFeatureMapper.countAllFeature();// 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        List<Feature> records = adminFeatureMapper.queryFeatureList(pager.getOffset(), pageSize);

        Page<Feature> brandPage = new Page<Feature>();
        brandPage.setRecords(records);
        brandPage.setSize(pageSize);
        brandPage.setCurrent(currentPage);
        brandPage.setTotal(totalCount);
        return brandPage;
    }

    @Override
    public int addFeatureOption(FeatureOption featureOption) {
        return 0;
    }

    @Override
    public Page<FeatureOption> queryFeatureOptionList(Page page) {
        return null;
    }

    @Override
    public List<Feature> queryFeatureByCategoryCode(String categoryCode) {

        List<Feature> records = adminFeatureMapper.queryFeatureListByCategory(categoryCode);

        for (Feature feature : records) {
            List<FeatureOption> featureOptions = adminFeatureMapper.queryFeatureOption(feature.getId());
            feature.setFeatureOptions(featureOptions);
        }
        return records;
    }

    @Override
    public int deleteById(long featureId) {
        int deleteResult = adminFeatureMapper.deleteById(featureId);

        // 删除deleteOption
        if(deleteResult==1){
            adminFeatureMapper.deleteOptionById(featureId);
        }

        return deleteResult;


    }

    @Override
    public int deleteOptionById(long featureId) {
        return 0;
    }



}
