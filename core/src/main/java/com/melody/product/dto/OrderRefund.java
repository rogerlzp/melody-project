package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderRefund implements Serializable{
    private static final long serialVersionUID = 3230925201809211220L;

    private Long id;

    private String orderNo;

    private String refundStatus;

    private Integer askedAmount;

    private Integer realAmount;

    private Date createDate;

    private Date updateDate;


}