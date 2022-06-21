package com.ktp.main.config.exception;

public enum TransExceptionCode{
    ERROR(13000,"异常"),
    AUTH_FAIL(13100,"token验证失败"),
    AUTH_TIME_OUT(13101, "token已过期");

    public int code;

    public String msg;

    TransExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
