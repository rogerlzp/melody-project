package com.melody.admin.api;


import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;


public interface AdminSKUService {

    String addSKU(SKU sku);

    int updateSKU(SKU sku);

    Page<SKU> querySKUList(Page page);

    // 根据 spuId来删除
    int deleteBySPUId(Long spuId);

    // 根据skuNo来删除
    int deleteById(String skuNo);


   SKU querySKUBySkuNo(String skuNo);

   List<SKU> querySKUBySpuCode(String spuCode);

   List<SKU> searchSKUListByName(String skuName);
}
