package com.melody.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class GeneralResult implements Serializable {
    private static final long serialVersionUID = -6024202655672249156L;
    private String code;
    private String message;

    private Integer totalCount;


}
