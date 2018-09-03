package com.melody.result;

import lombok.Data;

/**
 * 统一返回实体
 *
 * @author konghang
 */
@Data
public class JsonApi<T> {

    private static final int OK = 1;
    private static final int FAIL = 0;

    private int code = OK;
    private T data;
    private String message = "请求成功！";

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonApi() {
    }

    public static JsonApi isOk() {
        return new JsonApi();
    }

    public static JsonApi isFail() {
        return isFail(FAIL);
    }

    public static JsonApi isFail(Integer code) {
        return new JsonApi().code(code);
    }

    public static JsonApi isFail(Throwable e) {
        return isFail().message(e);
    }

    public static JsonApi isFail(ErrorCode errorCode) {
        return new JsonApi()
                .code(errorCode.getErrorID())
                .message(errorCode.getErrorMsg());
    }

    public JsonApi code(int code) {
        this.setCode(code);
        return this;
    }

    public JsonApi message(Throwable e) {
        this.setMessage(e.getMessage());
        return this;
    }

    public JsonApi message(String message) {
        this.setMessage(message);
        return this;
    }

    public JsonApi data(T data) {
        this.setData(data);
        return this;
    }
}
