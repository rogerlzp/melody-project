package com.melody.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.melody.product.dto.SKU;
import com.melody.product.dto.SPU;
import com.melody.product.dto.SkuImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AdminSKUMapper {

    int insert(SKU sku);

    // 插入图片
    int insertSKUImage(SkuImage skuImage);

    List<SKU> querySKUList(@Param(value = "offset") Integer offset,
                           @Param(value = "pageSize") Integer pageSize, @Param(value = "status") String status);


    int countAllSKU();

    int deleteBySPUId(@Param(value = "id") Long id);

    int insertSKUFeature(@Param(value = "id") Long id,
                         @Param(value = "skuId") Long skuId,
                         @Param(value = "featureOptionId") Long featureOptionId);


    int updateSKU(@Param(value = "skuNo") String skuNo, @Param(value = "status") String status);
}
