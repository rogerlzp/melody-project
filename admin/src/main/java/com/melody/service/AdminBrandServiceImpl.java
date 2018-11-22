package com.melody.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminBrandService;
import com.melody.common.utils.StringUtils;
import com.melody.dao.AdminBrandMapper;
import com.melody.product.dto.Brand;
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
        brand.setStatus("1");

        if (StringUtils.isEmpty(brand.getBrandCode())) {
            brand.setBrandCode("BC_"+brandId);
        }

        int insertResult = adminBrandMapper.insert(brand);
        if(insertResult == 1){
            return brandId;
        }
        return -1;
    }

    @Override
    public Page<Brand> queryBrandList(Page page) {
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



    // 产品状态位设置为-1， 不被搜索到
    @Override
    public int deleteBrandById(int brandId) {
        String status = "-1"; //表示删除
        int result = adminBrandMapper.updateBrand(brandId, status);
        return result;
    }

    @Override
    public Brand getBrandById(int brandId) {
        Brand brand = adminBrandMapper.selectByPrimaryKey(brandId);
        return  brand;
    }

    @Override
    public int updateBrand(Brand brand) {
        if (brand.getId() != null) {
            int updateResult = adminBrandMapper.updateByPrimaryKeySelective(brand);
            if(updateResult ==1 ){
                return updateResult;
            }
        }
        return -1;
    }


}

