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

    private String productPlace;

    private Date chuchangTime;

    private Date expirationTime;

    private Integer baozhiDay;

    private Integer totalNumber;

    private Integer inventory;

    private String productDesc;

    private String status;

    private BigDecimal price;

    private String spuCode;

    private List<FeatureOption> featureOptionList;
    private List<SkuImage> skuImageList;

    private Date createDate;
    private Date updateDate;

}