package com.melody.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.melody.admin.api.AdminBrandService;
import com.melody.admin.api.AdminSKUService;
import com.melody.dao.AdminBrandMapper;
import com.melody.dao.AdminSKUMapper;
import com.melody.dao.SequenceDao;
import com.melody.product.dto.Brand;
import com.melody.product.dto.SKU;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(group = "adminBrandService",timeout = 10000)
public class AdminBrandServiceImpl implements AdminBrandService {

    @Autowired
    AdminBrandMapper adminBrandMapper;

    @Autowired
    BaseServiceImpl baseService;

    @Override
    public int addBrand(Brand brand) {
        int brandId = baseService.getNextSequence("TT_BRAND").intValue();
        brand.setId(brandId);

        int insertResult = adminBrandMapper.insert(brand);
        return insertResult;
    }

    @Override
    public Page<Brand> queryBrandList(Page page) {

//        List<Brand> records = adminBrandMapper.queryBrandList(page.getCurrent(), page.getSize());

        int currentPage = page.getCurrent()-1; // current为1， 所以往后减一位

        int pageSize = page.getSize();
        int totalCount = adminBrandMapper.countAllBrand();// 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        List<Brand> records = adminBrandMapper.queryBrandList(pager.getOffset(), pageSize);

        Page<Brand> brandPage = new Page<Brand>();
        brandPage.setRecords(records);
        brandPage.setSize(pageSize);
        brandPage.setCurrent(currentPage);
        brandPage.setTotal(totalCount);
        return brandPage;

    }


}

