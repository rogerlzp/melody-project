package com.melody.product.dto;

import com.melody.base.GeneralResult;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SKUDetailResult extends GeneralResult implements Serializable {

    private static final long serialVersionUID = 3230925201809061620L;

    private String skuNo;

    private Long id;

    private String skuName;

    private String productPlace;

    private Date chuchangTime;

    private Date expirationTime;

    private Integer baozhiDay;

    private Integer totalNumber;

//    private Integer inventory;

    private String productDesc;

    private String status;

    private BigDecimal price;

    private String spuCode;

    private List<FeatureOption> featureOptionList;
    private List<SkuImage> skuImageList;

    List<SkuFeature> skuFeatureList;
    // 价格表
    private SkuPrice skuPrice;

    // 库存表
    private SkuInventory skuInventory;



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

}