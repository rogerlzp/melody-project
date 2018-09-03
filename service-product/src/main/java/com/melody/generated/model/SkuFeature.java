package com.melody.generated.model;

public class SkuFeature {
    private Long id;

    private String skuCode;

    private Integer featureOptionId;

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

    public Integer getFeatureOptionId() {
        return featureOptionId;
    }

    public void setFeatureOptionId(Integer featureOptionId) {
        this.featureOptionId = featureOptionId;
    }
}