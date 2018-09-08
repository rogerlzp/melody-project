package com.melody.dao;

import com.melody.product.dto.Brand;
import com.melody.product.dto.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminCategoryMapper {

    int insert(Category category);

    List<Category> queryCategoryList(@Param(value = "offset") Integer offset,
                               @Param(value = "pageSize") Integer pageSize);

    int countAllCategory();

}
