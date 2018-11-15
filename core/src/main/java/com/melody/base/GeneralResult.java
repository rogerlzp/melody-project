package com.melody.base;

import com.melody.result.JsonApi;
import lombok.Data;

import java.io.Serializable;

@Data
public class GeneralResult<T> implements Serializable {
    private static final long serialVersionUID = -6024202655672249156L;
    private Integer code;
    private String message;

    private T data;
    private static Integer OK_CODE = 0;
    private static final String FAIL_CODE = "-1";
    private static final String UNKNOWN_ERROR = "9999";

    private static String resultMessage = "success!";
    private static String errotMessage = "服务器异常!";

    public static GeneralResult isOk() {
        return new GeneralResult().code(OK_CODE);
    }

    public static GeneralResult isFail(String code) {
        return new GeneralResult().code(code);
    }

    public static GeneralResult isFail() {
        return new GeneralResult().code(FAIL_CODE);
    }


    public GeneralResult code(String code) {
        this.setCode(Integer.parseInt(code));
        return this;
    }

    public GeneralResult code(Integer code) {
        this.setCode(code);
        return this;
    }


    public GeneralResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public GeneralResult data(T data) {
        if (data != null) {
            this.setData(data);
        }
        return this;
    }

    public static GeneralResult unknowError() {
        return new GeneralResult().code(UNKNOWN_ERROR).message(errotMessage);
    }


}
