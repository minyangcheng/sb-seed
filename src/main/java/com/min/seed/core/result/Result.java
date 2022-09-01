package com.min.seed.core.result;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public static <T> Result<T> newInstance(ResultCode resultCode) {
        return new Result<T>().setCode(resultCode);
    }

    public static <T> Result<T> newInstance(ResultCode resultCode, String message) {
        return new Result<T>().setCode(resultCode).setMessage(message);
    }

    public static <T> Result<T> newInstance(ResultCode resultCode, T data, String message) {
        return new Result<T>().setCode(resultCode).setData(data).setMessage(message);
    }

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
