package com.melody.gateway.dto;

import com.melody.base.GeneralResult;
import lombok.Data;

import java.io.Serializable;

@Data
public class QiNiuTokenResult extends GeneralResult implements Serializable {
    private static final long serialVersionUID = 1019394959909071140L;
    private String uploadToken;

    public QiNiuTokenResult() {
    }
}
