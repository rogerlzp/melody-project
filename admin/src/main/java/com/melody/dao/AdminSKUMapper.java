package com.melody.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.melody.product.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AdminSKUMapper {

    int insert(SKU sku);


    int insertSKUInventory(SkuInventory skuInventory);
    int insertSKUPrice(SkuPrice skuPrice);
    int insertUserSKUDiscount(UserSkuDiscount userSkuDiscount);

    // 插入图片
    int insertSKUImage(SkuImage skuImage);

    List<SKU> querySKUList(@Param(value = "offset") Integer offset,
                           @Param(value = "pageSize") Integer pageSize, @Param(value = "status") String status);


    int countAllSKU();

    int deleteBySPUId(@Param(value = "id") Long id);

    int insertSKUFeature(@Param(value = "id") Long id,
                         @Param(value = "featureOptionId") Long featureOptionId,
                         @Param(value = "skuNo") String skuNo);


    int updateSKU(@Param(value = "skuNo") String skuNo, @Param(value = "status") String status);
    int updateSKUNum(@Param(value = "skuNo") String skuNo, @Param(value = "num") Integer num);
}
