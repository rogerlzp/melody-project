package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SkuImage implements Serializable {

    private static final long serialVersionUID = 3230925201809081914L;

    private Long id;

    private String skuNo;

    private int isMain;

    private String picUrl;

    private int picSeq;

    private Date createTime;


}