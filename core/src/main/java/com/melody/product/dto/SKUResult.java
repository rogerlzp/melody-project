package com.melody.product.dto;

import com.melody.base.GeneralResult;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SKUResult extends GeneralResult implements Serializable {
    private static final long serialVersionUID = 3230925201809131542L;

    List<SKU> records;

    private String spuName;
    private String categoryName;


}
