package com.melody.product.dto;


import com.melody.base.GeneralEnter;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SKUEnter extends GeneralEnter implements Serializable {

    private static final long serialVersionUID = 3230925201809101220L;

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


    private SkuAttr skuAttr;
    private List<SkuImage> skuImageList;

    // 价格表
    private SkuPriceEnter skuPriceEnter;

    // 库存表
    private SkuInventory skuInventory;

    private Date createDate;
    private Date updateDate;


}
