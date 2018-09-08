package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 3230925201809051008L;

    private Long id;

    private String categoryName;

    private String categoryCode;

    private Long parentId;


}