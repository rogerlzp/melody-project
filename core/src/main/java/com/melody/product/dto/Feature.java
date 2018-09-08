package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Feature implements Serializable {
    private static final long serialVersionUID = 3230925201809061619L;

    private Integer id;

    private String featureName;

    private String categoryCode;
    private String categoryName;


    private List<FeatureOption> featureOptions;


}