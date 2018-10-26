package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FeatureOption implements Serializable {
    private static final long serialVersionUID = 3230925201809062357L;

    private Long id;
    private Integer featureId;
    private String featureValue;  // 这个值可能是整数类型

}
