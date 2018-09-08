package com.melody.admin.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.melody.product.dto.Brand;
import com.melody.product.dto.Category;

public interface AdminCategoryService {

    int addCategory(Category category);

    Page<Category> queryCategoryList(Page page);

}
