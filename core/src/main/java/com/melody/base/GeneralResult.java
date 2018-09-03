package com.melody.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class GeneralResult implements Serializable {
    private static final long serialVersionUID = -6024202655672249156L;
    private String code;
    private String message;
    private Integer totalCount;

    public GeneralResult(String code) {
        this.code = code;
    }

    public GeneralResult(String code, String message, Integer totalCount) {
        this.code = code;
        this.message = message;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public GeneralResult() {
    }

    public GeneralResult(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
