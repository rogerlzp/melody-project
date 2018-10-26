package com.melody.product.api;

import com.melody.annotation.Role;
import com.melody.base.GeneralEnter;
import com.melody.product.dto.*;

import java.util.List;

public interface SKUService {

    @Role
    List<SKU> getSKU(GeneralEnter generalEnter);


    List<SKU> getSKUWithoutUserInfo(GeneralEnter generalEnter);

    SKUResult getSKUHomePage(GeneralEnter generalEnter);

    SKUResult getSKUHomeBySpu(SKUEnter skuEnter);

    SKUDetailResult getSKUDetail(SKUEnter skuEnter);

    Double getMySKUDiscount(String skuNo, Long userId);

    /**
     * 获取SKU详情和
     * 该SPU下对应的SKU的属性分类，价格和库存
     *
     * @param skuListEnter
     * @return
     */
    SKUListResult getSKUListFilter(SKUListEnter skuListEnter);

}
