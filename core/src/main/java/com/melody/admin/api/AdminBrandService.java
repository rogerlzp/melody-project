package com.melody.admin.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.dto.SysUser;
import com.melody.product.dto.Brand;

public interface AdminBrandService {

    int addBrand(Brand brand);

    Page<Brand> queryBrandList(Page page);

    int deleteBrandById(int brandId);

    Brand getBrandById(int brandId);

    int updateBrand(Brand brand);


}
