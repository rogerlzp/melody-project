package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1003925201809201140L;

    private Long id;

    private String classId;

    private String orderNo;

    private Double price;

    private String status;


}