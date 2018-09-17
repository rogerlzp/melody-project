package com.melody.generated.model;

import java.math.BigDecimal;

public class OrderRefund {
    private Long id;

    private String orderNo;

    private String refundStatus;

    private BigDecimal askedAmount;

    private BigDecimal realAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
    }

    public BigDecimal getAskedAmount() {
        return askedAmount;
    }

    public void setAskedAmount(BigDecimal askedAmount) {
        this.askedAmount = askedAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }
}