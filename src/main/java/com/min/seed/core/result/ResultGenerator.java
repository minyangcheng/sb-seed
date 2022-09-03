package com.min.seed.core.result;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    public static Result genSuccessResult() {
        return genResult(ResultCode.SUCCESS);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return genResult(ResultCode.SUCCESS.code(), data, ResultCode.SUCCESS.message());
    }

    public static <T> Result<T> genSuccessResult(T data, String message) {
        return genResult(ResultCode.SUCCESS.code(), data, message);
    }

    public static Result genFailResult() {
        return genResult(ResultCode.FAIL);
    }

    public static Result genFailResult(String message) {
        return genResult(ResultCode.FAIL.code(), message);
    }

    public static <T> Result<T> genResult(ResultCode resultCode) {
        return genResult(resultCode.code(), resultCode.message());
    }

    public static <T> Result<T> genResult(ResultCode resultCode, String message) {
        return genResult(resultCode.code(), message);
    }

    public static <T> Result<T> genResult(int code, String message) {
        return genResult(code, null, message);
    }

    public static <T> Result<T> genResult(int code, T data, String message) {
        return new Result().setCode(code).setMessage(message).setData(data);
    }

}
