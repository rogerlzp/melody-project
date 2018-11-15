package com.melody.user.dto;

import com.melody.base.GeneralResult;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterResult implements Serializable {
    private static final long serialVersionUID = 1620966942868460193L;
    private String pictureCode;
    private String sessionKey;
    private String plain;
    private String url;
    private String sign;

    public RegisterResult() {
    }

}
