package com.melody.result;

import com.alibaba.fastjson.JSON;

import java.util.Hashtable;

/**
 * 状态枚举类
 *
 * @author konghang
 */
public enum ErrorCode {

    RESULT_OK(0, "正常返回"),
    RESULT_FAIL(1, "存在未错误"),
    CORE_SERVICE_FAIL(2, "服务错误"),

    INTERNET_ERROR_400(400, "Bad Request!"),
    INTERNET_ERROR_401(401, "NotAuthorization"),
    INTERNET_ERROR_405(405, "Method Not Allowed"),
    INTERNET_ERROR_406(406, "Not Acceptable"),
    INTERNET_ERROR_500(500, "Internal Server Error"),

    /*token*/
    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_INVALID_CLAIM(701, "token过期(Claim),请重新登录"),
    TOKEN_ERROR(702, "token校验失败"),
    TOKEN_SIGNATURE_ERROR(703, "签名校验失败"),

    /*参数*/
    PARAM_IS_NULL(800, "参数为空"),

    /*图片*/
    IMAGE_FORMAT_ERROR(900, "图片格式不正确"),

    /*用户模块开始*/
    USER_MODEL_BEGIN(1000, "用户模块错误码开始"),
    USER_NOT_ACTIVATION(1001, "用户还没有激活,请查看邮件激活用户."),
    USER_UNKNOWN_ACCOUNT(1002, "未知账户"),
    USER_ERROR_PASSWORD(1003, "错误的凭证,密码错误"),
    USER_ERROR_PASSWORD_MUCH(1004, "用户名或密码错误次数过多"),
    USER_ACCOUNT_LOCKED(1005, "账户已锁定"),
    USER_ERROR_ACCOUNT_OR_PASSWORD(1006, "用户名或密码不正确"),
    USER_NAME_EXIST(1007, "用户名已存在"),
    USER_EMAIL_EXIST(1008, "邮箱已存在"),
    USER_UNAUTHORIZED(1009, "授权失败"),
    USER_CREATE_ERROR(1010, "用户创建失败"),
    USER_ROLE_CREATE_ERROR(1011, "用户角色添加失败"),
    USER_MODEL_END(1999, "用户模块错误码结束"),
    /*用户模块结束*/

    /*文章模块开始*/
    ARTICLE_MODEL_BEGIN(2000, "文章模块错误码开始"),
    ARTICLE_ID_ERROR(2000, "无效文章ID"),
    ARTICLE_MODEL_END(2999, "文章模块错误码开始"),
    /*文章模块结束*/

    RUNTIME_ERROR(1000, "[服务器]运行时异常"),
    NULL_POINT_ERROR(1001, "[服务器]空值异常"),
    INTERNET_ERROR(1002, "[服务器]网络异常"),

    SYSTEM_BEGIN(91000, "系统模块开始"),

    TRANSACTION_ERROR(92000, "事务异常"),

    SYSTEM_ERROR(91500, "系统错误"),

    SYSTEM_FAIL(91599, "系统相关"),

    ERROR_MAX(999999999, "错误");

//    @Setter
//    @Getter
    private int errorID;

//    @Setter
//    @Getter
    private String errorMsg;

    public int getErrorID() {
        return this.errorID;
    }

    public void setErrorID(int errorID) {
        this.errorID = errorID;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    ErrorCode(int id, String msg) {
        this.errorID = id;
        this.errorMsg = msg;
    }

    public Hashtable<Object, Object> toJson() {
        Hashtable<Object, Object> json = new Hashtable<Object, Object>();
        json.put("error", errorID);
        json.put("msg", errorMsg);
        return json;
    }

    @Override
    public String toString() {
        JSON.toJSONString(toJson());
        return JSON.toJSONString(toJson());
    }

    public static Integer getErrorIdByErrorMsg(String errorMsg) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorMsg.equals(errorCode.errorMsg)) {
                return errorCode.errorID;
            }
        }
        return null;
    }
}
