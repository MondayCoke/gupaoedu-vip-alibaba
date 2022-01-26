package com.gupaoedu.gpmall.exception;

import lombok.Data;

/**
 * 业务处理类异常
 */
@Data
public class BizException extends RuntimeException{

    /**返回码*/
    private String errorCode;
    /**信息*/
    private String errorMessage;

    public BizException() {
        super();
    }

    public BizException(String errorCode) {
        super(errorCode);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public BizException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = message;
    }
}
