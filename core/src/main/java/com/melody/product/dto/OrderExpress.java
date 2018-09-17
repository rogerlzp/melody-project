package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class OrderExpress implements Serializable {
    private static final long serialVersionUID = 3230925201809151748L;
    private Long id;

    private String orderNo;

    private String receiverName;

    private String addressDetail;

    private String receiverPhone;

    private String zipcode;

    private String develieryCompany;

    private Date develieryTime;
    private String status;

}