package com.gupaoedu.gpmall.exception;

import lombok.Data;

/**
 * 参数验证类异常
 */
@Data
public class ValidException extends RuntimeException{

    /**返回码*/
    private String errorCode;
    /**信息*/
    private String errorMessage;

    public ValidException() {
        super();
    }

    public ValidException(String errorCode) {
        super(errorCode);
    }

    public ValidException(Throwable cause) {
        super(cause);
    }

    public ValidException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ValidException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public ValidException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

}
