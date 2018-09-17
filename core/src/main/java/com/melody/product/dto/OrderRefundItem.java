package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderRefundItem implements Serializable {
    private static final long serialVersionUID = 3230925201809151752L;

    private Long id;
    private String orderNo;
    private Long refundOrderId;
    private String skuNo;
    private Long orderSkuId;
    private Integer refundNum;

}