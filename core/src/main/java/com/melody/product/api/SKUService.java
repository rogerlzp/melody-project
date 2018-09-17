package com.melody.product.api;

import com.melody.annotation.Role;
import com.melody.base.GeneralEnter;
import com.melody.product.dto.SKU;
import com.melody.product.dto.SKUDetailResult;
import com.melody.product.dto.SKUEnter;
import com.melody.product.dto.SKUResult;

import java.util.List;

public interface SKUService {

    @Role
    List<SKU> getSKU(GeneralEnter generalEnter);


    List<SKU> getSKUWithoutUserInfo(GeneralEnter generalEnter);

    SKUResult getSKUHomePage(GeneralEnter generalEnter);

    SKUResult getSKUHomeBySpu(SKUEnter skuEnter);

    SKUDetailResult getSKUDetail(SKUEnter skuEnter);

    Double getMySKUDiscount(String skuNo, Long userId);

}
