package com.melody.dao;

import com.melody.product.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SKUMapper {
    int deleteByPrimaryKey(String skuNo);

    int insert(SKU record);

    int insertSelective(SKU record);

    SKU selectByPrimaryKey(@Param(value = "skuNo") String skuNo);

    int updateByPrimaryKeySelective(SKU record);

    int updateByPrimaryKey(SKU record);

    List<SKU> querySKUList(@Param(value = "offset") Integer offset,
                           @Param(value = "pageSize") Integer pageSize,
                           @Param(value = "status") String status);


    int countAllSKU();

    int countAllSKUBySpu(@Param(value = "spuCode") String spuCode);

    List<SKU> querySKUListBySpu(@Param(value = "offset") Integer offset,
                                @Param(value = "pageSize") Integer pageSize,
                                @Param(value = "spuCode") String spuCode,
                                @Param(value = "status") String status);



    List<SkuImage> getSkuImageList(@Param(value = "skuNo") String skuNo);
    List<SkuFeature> getSkuFeatureList(@Param(value = "skuNo") String skuNo);

    Double getSkuDiscount(@Param(value = "skuNo") String skuNo, @Param(value = "userId") Long userId);

    OrderSkuResult getOrderSkuResult(@Param(value = "skuNo") String skuNo);

    int updateSkuNum(@Param(value = "skuNo") String skuNo, @Param(value = "num") Integer num);

    // getPriceBySkuNo

    Double getPriceBySkuNo(@Param(value = "skuNo") String skuNo);

    List<SKU> getAllSKUBySpu(@Param(value = "spuCode") String spuCode, @Param(value = "status") String status );


    String getSPUCodeBySKUNo(@Param(value = "skuNo") String skuNo);
}