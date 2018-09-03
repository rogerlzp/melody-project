package com.melody.generated.model;

import java.math.BigDecimal;
import java.util.Date;

public class SKU {
    private String skuNo;

    private Long id;

    private String skuName;

    private String productPlace;

    private Date chuchangTime;

    private Date expirationTime;

    private Integer baozhiDay;

    private Integer totalNumber;

    private Integer inventory;

    private String productDesc;

    private String status;

    private BigDecimal price;

    private String spuCode;

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo == null ? null : skuNo.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getProductPlace() {
        return productPlace;
    }

    public void setProductPlace(String productPlace) {
        this.productPlace = productPlace == null ? null : productPlace.trim();
    }

    public Date getChuchangTime() {
        return chuchangTime;
    }

    public void setChuchangTime(Date chuchangTime) {
        this.chuchangTime = chuchangTime;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Integer getBaozhiDay() {
        return baozhiDay;
    }

    public void setBaozhiDay(Integer baozhiDay) {
        this.baozhiDay = baozhiDay;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode == null ? null : spuCode.trim();
    }
}