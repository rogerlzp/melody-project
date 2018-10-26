package com.melody.product.dto;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.io.Serializable;
import java.util.Date;

@Data
public class OrderRefundItem implements Serializable {
    private static final long serialVersionUID = 3230925201809211223L;

    private Long id;

    private String orderNo;

    private Long refundOrderId;

    private String skuNo;

    private Long orderItemId;

    private Integer refundNum;

    private Integer totalNum;

    private String refundReason; // 退款理由

    private Integer askedAmount;

    private Integer realAmount;

    private Date createDate;

    private Date autitDate;
    private String auditResult;
    private String refundStatus;

}