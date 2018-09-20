package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 3230925201809151747L;

    private Long id;
    private String skuNo;
    private String orderNo;
    private Double amount;
    private Double originalPrice;
    private Double price;
    private Integer num;

    private String picUrl;
    private String skuName;
    private String spuName;

    // 这两个状态默认同Order是一样的。发货或者退货的时候在自行变化。
    private String status;
    private String refundStatus;


}