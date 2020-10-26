package com.shanezhou.pro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

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


    public ResultVO(int code, String message) {
        this(code, message, null);
    }

}
