package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SkuPrice  implements Serializable {

    private static final long serialVersionUID = 3230925201809091720L;

    private Long id;

    private String skuNo;

    private Double listPrice;

    private Double salePrice;

    private Double specialPrice;

    private Double importPrice;

    private Double costPrice;

    private Double discountPrice;

    private Date createDate;

    private Date updateDate;


}