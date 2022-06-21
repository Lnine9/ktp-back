package com.ktp.main.util;


import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;

enum Code {
    SUCCESS(200),
    FAIL(13);

    int code;

    Code(int code) {
        this.code = code;
    }
}

@Data
public class ResResult<T> {
    private int code;
    private String message;
    private T data;
    private Date time;

    public ResResult() {
    }

    public ResResult(int code) {
        this.code = code;
    }

    public ResResult(int code, String message, T data) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.setTime(new Date());
    }

    public ResResult(T data) {
        this.data = data;
    }

    public static <V> ResResult<V> fail(int status, String message, V result) {
        return new ResResult<>(status, message, result);
    }

    public static <V> ResResult<V> fail(String message) {
        ResResult<V> result = new ResResult<>();
        result.setMessage(message);
        result.setCode(Code.FAIL.code);
        return result;
    }

    public static <V> ResResult<V> fail(int status, String message) {
        ResResult<V> result = new ResResult<>();
        result.setCode(status);
        result.setMessage(message);
        return result;
    }

    public static <V> ResResult<V> ok() {
        ResResult<V> result = new ResResult<>();
        result.setCode(Code.SUCCESS.code);
        result.setMessage("success");
        return result;
    }

    public static <V> ResResult<V> ok(V data) {
        ResResult<V> result = new ResResult<>();
        result.setCode(Code.SUCCESS.code);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <V> ResResult<V> ok(String message, V data) {
        ResResult<V> result = new ResResult<>();
        result.setCode(Code.SUCCESS.code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <V> ResResult<V> ok(Integer status, String message) {
        ResResult<V> result = new ResResult<>();
        result.setCode(status);
        result.setMessage(message);
        result.setTime(new Date());
        return result;
    }

    public static <V> ResResult<V> ok(Integer status, String message, V data) {
        ResResult<V> result = new ResResult<>();
        result.setCode(status);
        result.setMessage(message);
        result.setData(data);
        result.setTime(new Date());
        return result;
    }

    public String toString() {
        return "RestDataResult{status =" + this.code + ", message =" + this.message + ", data =" + JSON.toJSONString(this.data) + ", time = " + this.time + "}";
    }
}
