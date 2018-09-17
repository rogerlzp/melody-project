package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Inventory  implements Serializable {
    private static final long serialVersionUID = 3230925201809092120L;

    private Long id;

    private String skuNo;

    private Integer totalNum;

    private Integer sellableNum;

    private Integer orderOccupiedNum;

    private Integer unavailableNum;

    private Integer lockedNum;

    private Date createDate;

    private Date updateDate;


}