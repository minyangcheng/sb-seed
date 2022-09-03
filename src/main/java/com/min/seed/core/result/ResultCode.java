package com.min.seed.core.result;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {

    SUCCESS(200, "成功"),

    FAIL(400, "失败"),

    UNAUTHORIZED(401, "未认证"),

    NOT_FOUND(404, "接口不存在"),

    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
