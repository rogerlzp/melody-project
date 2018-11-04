package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SPU implements Serializable {

    private static final long serialVersionUID = 3230925201809051006L;

    private Long id;

    private String spuName;

    private String spuCode;

    private String spuDesc;

    private String spuEnDesc;

    private String detail;

    private String categoryCode;

    private String brandCode;

    private String categoryName;

    private String brandName;

    private List<SpuAttr> spuAttrList;
    
}