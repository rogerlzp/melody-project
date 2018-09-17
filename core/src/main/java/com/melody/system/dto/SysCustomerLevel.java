package com.melody.system.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysCustomerLevel implements Serializable {

    private Integer id;
    private Integer levelId;
    private String levelName;
}



