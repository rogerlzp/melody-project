package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderRefund implements Serializable {

    private static final long serialVersionUID = 3230925201809151750L;

    private Long id;

    private String orderNo;

    private String refundStatus;

    private BigDecimal askedAmount;

    private BigDecimal realAmount;


}