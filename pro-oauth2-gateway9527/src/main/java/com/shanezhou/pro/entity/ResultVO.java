package com.shanezhou.pro.entity;

/**
 * @author ShaneZhou
 * @since 2020/9/30 周三
 */
public class ResultVO<T> {

    private int code;
    private String message;

    private T data;

    public ResultVO(int code, String message) {
        this(code, message, null);
    }

    public ResultVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
