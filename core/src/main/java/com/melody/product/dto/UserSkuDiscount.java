package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserSkuDiscount implements Serializable {

    private static final long serialVersionUID = 8918703201809010620L;

    private Long id;

    private String skuNo;

    private Integer userLevelId;

    private Double discount;

    private String status;

    private Date createDate;

    private Date updateDate;


}