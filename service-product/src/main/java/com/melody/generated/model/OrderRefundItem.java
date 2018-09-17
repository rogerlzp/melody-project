package com.melody.generated.model;

public class OrderRefundItem {
    private Long id;

    private String orderNo;

    private Long refundOrderId;

    private String skuNo;

    private Long orderSkuId;

    private Integer refundNum;

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

    public Long getRefundOrderId() {
        return refundOrderId;
    }

    public void setRefundOrderId(Long refundOrderId) {
        this.refundOrderId = refundOrderId;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo == null ? null : skuNo.trim();
    }

    public Long getOrderSkuId() {
        return orderSkuId;
    }

    public void setOrderSkuId(Long orderSkuId) {
        this.orderSkuId = orderSkuId;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }
}