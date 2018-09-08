package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Brand implements Serializable{
    private static final long serialVersionUID = 3230925201809051007L;

    private Integer id;

    private String brandName;

    private String brandCode;

    private String brandImageUrl;


}