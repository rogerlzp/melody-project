package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SPU implements Serializable {

    private static final long serialVersionUID = 3230925201809051006L;

    private Long id;

    private String spuName;  // 产品名字

    private String spuCode;  // 产品Code

    private String spuDesc;   // 产品描述

    private String spuEnDesc; // 产品英文描述

    private String detail;    // 产品详情

    private String categoryCode; // 产品分类 代码

    private String brandCode;    // 品牌

    private String categoryName;

    private String brandName;

    private List<SpuAttr> spuAttrList;
    private List<SpuComponent> spuComponentList;
}