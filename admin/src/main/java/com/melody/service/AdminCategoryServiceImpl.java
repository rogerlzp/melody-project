package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminCategoryService;
import com.melody.dao.AdminBrandMapper;
import com.melody.dao.AdminCategoryMapper;
import com.melody.product.dto.Brand;
import com.melody.product.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(group = "adminCategoryService", timeout = 10000)
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Autowired
    AdminCategoryMapper adminCategoryMapper;

    @Autowired
    BaseServiceImpl baseService;
    @Override
    public int addCategory(Category category) {
        long categoryId = baseService.getNextSequence("TT_CATEGORY");
        category.setId(categoryId);

        int insertResult = adminCategoryMapper.insert(category);
        return insertResult;
    }

    @Override
    public Page<Category> queryCategoryList(Page page) {
        int currentPage = page.getCurrent()-1; // current为1， 所以往后减一位
        int pageSize = page.getSize();
        int totalCount = adminCategoryMapper.countAllCategory();// 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        List<Category> records = adminCategoryMapper.queryCategoryList(pager.getOffset(), pageSize);

        Page<Category> brandPage = new Page<Category>();
        brandPage.setRecords(records);
        brandPage.setSize(pageSize);
        brandPage.setCurrent(currentPage);
        brandPage.setTotal(totalCount);
        return brandPage;
    }
}
