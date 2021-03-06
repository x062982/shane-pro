package com.shanezhou.pro.entity;

import com.shanezhou.pro.enums.ExceptionCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

/**
 * @author ShaneZhou
 * @since 2020/9/30 周三
 */
@Data
@AllArgsConstructor
public class ResultVO<T> {

    private Integer code;
    private String message;

    private T data;

    public ResultVO (T data) {
        this(ExceptionCodeEnum.SUCCESS, data);
    }

    public ResultVO (String message) {
        this(ExceptionCodeEnum.SUCCESS.getCode(), message);
    }

    public ResultVO (ExceptionCodeEnum code, T data) {
        this.code = code.getCode();
        this.message = code.getMsg();
        this.data = data;
    }

    public ResultVO (int code, String message) {
        this(code, message, null);
    }

}
