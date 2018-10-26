package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 3230925201809151745L;

    private Long id;
    private Long userId;
    private String orderNo;
    private String status;
    private String refundStatus;
    private Date createTime;
    private Date updateTime;
    private Integer totalNum;
    private Double totalDiscount;
    private Double totalAmount;
    private Double expressFee;
    private Integer birdCoin; // 积分
    private Date paidTime; //付款时间


    private List<OrderItem> orderItemList;
}