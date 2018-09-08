package com.melody.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.melody.product.dto.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminBrandMapper {

    Brand selectByPrimaryKey(int id);

    int deleteByPrimaryKey(int id);

    int insert(Brand brand);

    int insertSelective(Brand brand);

    int updateByPrimaryKeySelective(Brand brand);

    int updateByPrimaryKey(Brand brand);

    List<Brand> queryBrandList(@Param(value = "offset") Integer offset,
                               @Param(value = "pageSize") Integer pageSize);

    int countAllBrand();
}
