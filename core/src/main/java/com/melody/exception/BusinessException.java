package com.melody.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 345573995122478L;
    private String errorCode = null;
    private String[] variables = null;

    public BusinessException() {
    }

    public BusinessException(String errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, String[] variables) {
        this.errorCode = errorCode;
        this.variables = variables == null ? null : (String[])variables.clone();
    }

    public BusinessException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public BusinessException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode.toString();
    }

    public BusinessException(String errorCode, String errorMessage, String[] variables) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.variables = variables == null ? null : (String[])variables.clone();
    }

    public BusinessException(String errorCode, Throwable t) {
        super(t);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, String errorMessage, Throwable t) {
        super(errorMessage, t);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, String[] variables, Throwable t) {
        super(t);
        this.errorCode = errorCode;
        this.variables = variables == null ? null : (String[])variables.clone();
    }

    public BusinessException(String errorCode, String errorMessage, String[] variables, Throwable t) {
        super(errorMessage, t);
        this.errorCode = errorCode;
        this.variables = variables == null ? null : (String[])variables.clone();
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("errorCode[").append(this.errorCode).append("]\n");
        if (this.variables != null && this.variables.length != 0) {
            sb.append("variables[");

            for(int i = 0; i < this.variables.length; ++i) {
                sb.append(this.variables[i]);
            }

            sb.append("]\n");
        }

        sb.append(super.toString());
        return sb.toString();
    }
}