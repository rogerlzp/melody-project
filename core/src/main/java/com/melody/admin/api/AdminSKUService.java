package com.melody.admin.api;


import com.baomidou.mybatisplus.plugins.Page;
import com.melody.product.dto.SKU;
import com.melody.product.dto.SPU;

public interface AdminSKUService {

    String addSKU(SKU sku);

    Page<SKU> querySKUList(Page page);

    // 根据 spuId来删除
    int deleteBySPUId(Long spuId);

    // 根据skuNo来删除
    int deleteById(String skuNo);

}