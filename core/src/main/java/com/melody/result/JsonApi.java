package com.melody.result;

import com.melody.exception.BusinessException;
import lombok.Data;

import java.util.Map;

/**
 * 统一返回实体
 *
 * @author konghang
 */
@Data
public class JsonApi<T> {

    private static final int OK = 0;
    private static final int FAIL = -1;
    private static final int UNKNOWN_ERROR = 9999;

    private int resultCode = OK;

    private T data;
    private String resultMessage = "请求成功！";


    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public JsonApi() {
    }

    public static JsonApi isOk() {
        return new JsonApi();
    }

//    public static JsonApi isFail() {
//        return isFail(FAIL);
//    }

    public static JsonApi isFail(Integer code) {
        return new JsonApi().code(code);
    }

//    public static JsonApi isFail(Throwable e) {
//        return isFail().message(e);
//    }

    public static JsonApi isFail(ErrorCode errorCode) {
        return new JsonApi()
                .code(errorCode.getErrorID())
                .message(errorCode.getErrorMsg());
    }
//
//    public JsonApi code(String code) {
//        this.setResultCode(code);
//        return this;
//    }
    public JsonApi code(int code) {
        this.setResultCode(code);
        return this;
    }


    public JsonApi message(Throwable e) {
        this.setResultMessage(e.getMessage());
        return this;
    }

    public JsonApi message(String message) {
        this.setResultMessage(message);
        return this;
    }

    public JsonApi data(T data) {
        this.setData(data);
        return this;
    }

    public static JsonApi isFail(int errorCode, String message) {
        return new JsonApi()
                .code(errorCode)
                .message(message);
    }
    public static JsonApi unknowError() {
        return new JsonApi()
                .code(UNKNOWN_ERROR)
                .message("服务器异常");
    }
}
