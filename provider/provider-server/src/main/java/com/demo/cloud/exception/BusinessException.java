package com.demo.cloud.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 蓝健
 * @version 1.0
 * @date 2020/7/27 15:52
 */
@Getter@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private String msg;
    private Integer code;
    
    public BusinessException(String message) {
        super(message);
        this.msg = message;
        this.code = 500;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
        this.code = 200;
    }
}
