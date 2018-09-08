package com.melody.gateway.dto;

import com.melody.base.GeneralEnter;
import lombok.Data;

import java.io.Serializable;

@Data
public class QiNiuTokenEnter extends GeneralEnter implements Serializable {
    private static final long serialVersionUID = 5130193201809071141L;
    private String spaceName;
    private String fileName;

    public QiNiuTokenEnter() {
    }
}
