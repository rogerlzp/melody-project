package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 1002925201809201140L;

    private Long id;

    private String orderNo;
    private String status;

    private Long userId;

    private Double totalAmount;

    private Date paidTime;

    private Date createDate;

    private Date updateDate;


}