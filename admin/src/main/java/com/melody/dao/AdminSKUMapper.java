package com.melody.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.melody.product.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AdminSKUMapper {

    int insert(SKU sku);

    int updateSKUByPrimaryKey(SKU sku);

    int insertSKUInventory(SkuInventory skuInventory);
    int insertSKUPrice(SkuPrice skuPrice);
    int insertUserSKUDiscount(UserSkuDiscount userSkuDiscount);

    // 插入图片
    int insertSKUImage(SkuImage skuImage);
    int updateSkuImage(SkuImage skuImage);

    List<SKU> querySKUList(@Param(value = "offset") Integer offset,
                           @Param(value = "pageSize") Integer pageSize, @Param(value = "status") String status);


    int countAllSKU();

    int deleteBySPUId(@Param(value = "id") Long id);

    int insertSKUAttr(@Param(value = "id") Long id,
                         @Param(value = "skuNo") String skuNo,
                      @Param(value = "attributes") String attributes,
                      @Param(value = "picUrl") String picUrl);



    int insertSKUAttr(SkuAttr skuAttr);
    int updateSKUAttr(SkuAttr skuAttr);

    int updateSKU(@Param(value = "skuNo") String skuNo, @Param(value = "status") String status);
    int updateSKUNum(@Param(value = "skuNo") String skuNo, @Param(value = "num") Integer num);

    SKU querySKUBySkuNo(@Param(value = "skuNo") String skuNo);
    SkuAttr getSkuAttrBySkuNo(@Param(value = "skuNo") String skuNo);

    List<SkuImage> getSkuImageList(@Param(value = "skuNo") String skuNo);

}

