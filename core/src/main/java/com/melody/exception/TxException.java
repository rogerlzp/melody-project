package com.melody.exception;

import com.melody.result.ErrorCode;
import lombok.Getter;
import lombok.Setter;


/**
 * 事务回滚异常,通过继承Exception成为受检异常,service抛出异常,
 * 强制controller调用者进行异常捕获
 * @author konghang
 */

public class TxException extends Exception {

    @Getter
    @Setter
    private ErrorCode errorCode;

    public TxException(){
        super(ErrorCode.CORE_SERVICE_FAIL.getErrorMsg());
        this.errorCode = ErrorCode.CORE_SERVICE_FAIL;
    }

    public TxException(ErrorCode errorCode){
        super();
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
