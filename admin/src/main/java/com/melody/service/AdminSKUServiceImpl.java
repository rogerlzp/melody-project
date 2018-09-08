package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.melody.admin.api.AdminSKUService;
import com.melody.admin.api.AdminSPUService;
import com.melody.admin.api.SysPermService;
import com.melody.admin.dto.SysPerm;
import com.melody.dao.AdminSKUMapper;
import com.melody.dao.AdminSPUMapper;
import com.melody.dao.SysPermMapper;
import com.melody.product.dto.FeatureOption;
import com.melody.product.dto.SKU;
import com.melody.product.dto.SPU;
import com.melody.product.dto.SkuImage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(group = "adminSKUService")
public class AdminSKUServiceImpl implements AdminSKUService {

    @Autowired
    AdminSKUMapper adminSKUMapper;

    @Autowired
    BaseServiceImpl baseService;

    @Override
    public String addSKU(SKU sku) {
        long skuId = baseService.getNextSequence("TT_FEATURE");
        sku.setId(skuId);

        // SKU NO
        String skuNo = "SKU" + skuId;
        sku.setSkuNo(skuNo);

        sku.setStatus("1"); // 新建，有效

        int insertResult = adminSKUMapper.insert(sku);

        // 添加属性成功后，在添加特性
        if (insertResult == 1) {
            if (sku.getFeatureOptionList() != null) {
                for (FeatureOption featureOption : sku.getFeatureOptionList()) {
                    long skuFeatureId = baseService.getNextSequence("TR_SKU_FEATURE");
                    adminSKUMapper.insertSKUFeature(skuFeatureId, featureOption.getId(), skuId);
                }
            }
            if (sku.getSkuImageList() != null) {
                int seqId = 0;
                for (SkuImage skuImage : sku.getSkuImageList()) {
                    long skuImageId = baseService.getNextSequence("TR_SKU_IMAGE");
                    skuImage.setIsMain(seqId == 0 ? 1 : 0);
                    skuImage.setPicSeq(seqId++);
                    skuImage.setId(skuImageId);
                    skuImage.setSkuNo(skuNo);
                    adminSKUMapper.insertSKUImage(skuImage);
                }
            }
        } else {
            // TODO: add fail later
        }
        return skuNo;
    }


    @Override
    public Page<SKU> querySKUList(Page page) {
        int currentPage = page.getCurrent() - 1; // current为1， 所以往后减一位
        int pageSize = page.getSize();
        int totalCount = adminSKUMapper.countAllSKU();// 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        String validStatus = "1";
        List<SKU> records = adminSKUMapper.querySKUList(pager.getOffset(), pageSize, validStatus);

        Page<SKU> brandPage = new Page<SKU>();
        brandPage.setRecords(records);
        brandPage.setSize(pageSize);
        brandPage.setCurrent(currentPage);
        brandPage.setTotal(totalCount);
        return brandPage;
    }

    @Override
    public int deleteBySPUId(Long spuId) {
        int result = adminSKUMapper.deleteBySPUId(spuId);
        return result;
    }

    // 产品状态位设置为-1， 不被搜索到
    @Override
    public int deleteById(String skuNo) {
        String status = "-1"; //表示删除
        int result = adminSKUMapper.updateSKU(skuNo, status);
        return result;
    }

}

