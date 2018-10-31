package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Brand implements Serializable{
    private static final long serialVersionUID = 3230925201809051007L;

    private Integer id;

    private String brandName;  // 品牌名字
    private String brandEnName;  // 品牌英文名字

    private String brandDesc;  // 品牌描述
    private String brandEnDesc;  // 品牌英文描述

    private String brandCode;

    private String brandLogo;

    private String status;

    private String homepage;

    private String brandStory;

    private Integer parentId;
    private Date createDate;
    private Date updateDate;
}