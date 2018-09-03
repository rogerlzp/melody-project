package com.melody.product.dto;

import java.util.Date;

public class SKU_IMAGE {
    private Long id;

    private String skuCode;

    private Byte isMain;

    private String picUrl;

    private Byte picSeq;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public Byte getIsMain() {
        return isMain;
    }

    public void setIsMain(Byte isMain) {
        this.isMain = isMain;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Byte getPicSeq() {
        return picSeq;
    }

    public void setPicSeq(Byte picSeq) {
        this.picSeq = picSeq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}