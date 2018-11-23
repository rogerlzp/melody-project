package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SKU implements Serializable {
    private static final long serialVersionUID = 3230925201809061620L;
    private String skuNo;
    private Long id;
    private String skuName;

    private Integer totalNumber;
    private String skuDesc;

    private String skuEnDesc;

    private String status;

    private Double price;

    private String spuCode;

    private SkuAttr skuAttr;
    private List<SkuImage> skuImageList;

    // 价格表
    private SkuPrice skuPrice;

    // 库存表
    private SkuInventory skuInventory;

    // 价格表
    private SkuPriceEnter skuPriceEnter;

    // 数量，对外显示使用
    private Integer sellableNum;  // 和Inventory中的一样

    // 同SkuPrice 中的一样
    private Double listPrice;
    private Double salePrice;
    private Double specialPrice;

    // 主要的图片
    private String picUrl;  // 同SkuImage 中的一个

    private Date createDate;
    private Date updateDate;

    // 额外展示属性
    private String brandCode;
    private String brandName;
    private String brandLogo;
    private String categoryName;
    private String categoryCode;

    private String spuName;


    // 该SKU 对应的SPU对应的所有属性
    private List<SpuAttr> spuAttrList;

}


