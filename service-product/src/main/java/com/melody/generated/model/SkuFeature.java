package com.melody.generated.model;

public class SkuFeature {
    private Long id;

    private String skuNo;

    private Integer featureOptionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo == null ? null : skuNo.trim();
    }

    public Integer getFeatureOptionId() {
        return featureOptionId;
    }

    public void setFeatureOptionId(Integer featureOptionId) {
        this.featureOptionId = featureOptionId;
    }
}