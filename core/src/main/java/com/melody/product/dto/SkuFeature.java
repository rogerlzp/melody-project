package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuFeature implements Serializable {

    private static final long serialVersionUID = 3230925201809131048L;


    private Long id;

    private String skuNo;

    private Integer featureOptionId;

    private String featureName;
    private String featureValue;

}