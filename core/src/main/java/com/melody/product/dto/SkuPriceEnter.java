package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class SkuPriceEnter  implements Serializable {
    private static final long serialVersionUID = 9102819201809101320L;
    private Long id;

    private Double listPrice;

    private Double salePrice;

    private Double specialPrice;

    private Double importPrice;

    private Double costPrice;

    private List<Double> discountPriceList;




}