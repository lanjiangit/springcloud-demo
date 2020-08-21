package com.demo.cloud.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 蓝健
 * @version 1.0
 * @date 2020/7/27 15:48
 */
@ControllerAdvice
public class UnifyExceptionHandle {
    
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Object businessExceptionHandle(BusinessException e){
        return e.getMsg();
    }
    
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exceptionHandle(Exception e){
        return e.getMessage();
    }
}
