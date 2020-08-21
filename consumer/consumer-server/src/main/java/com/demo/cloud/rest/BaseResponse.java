package com.demo.cloud.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应基类
 * Created by aqlu on 15/11/30.
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {
    /**
     * 结果码
     */
    private String code;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 错误内容
     */
    private Object errorData;

    /**
     * 内容
     */
    private T context;

    /**
     * 特殊提示
     *
     * @param errorCode 异常码
     * @param message   消息
     * @param obj       业务错误的时候，但是依旧要返回数据
     * @return
     */
    public static BaseResponse info(String errorCode, String message, Object obj) {
        return new BaseResponse<>(errorCode, message, obj, null);
    }

    /**
     * 特殊提示
     *
     * @param errorCode 异常码
     * @param message   消息
     * @return
     */
    public static <T> BaseResponse<T> info(String errorCode, String message) {
        return new BaseResponse<>(errorCode, message, null, null);
    }

    /**
     * 失败
     *
     * @param message 消息
     * @return
     */
    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>("failed", message, null, null);
    }

    /**
     * 成功
     *
     * @param context 内容
     * @return
     */
    public static <T> BaseResponse<T> success(T context) {
        return new BaseResponse<>("successful", null, null, context);
    }

}
