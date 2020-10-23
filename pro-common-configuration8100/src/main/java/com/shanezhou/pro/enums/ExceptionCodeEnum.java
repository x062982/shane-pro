package com.shanezhou.pro.enums;

import lombok.Getter;

/**
 * @author ShaneZhou
 * @since 2020/9/25 周五
 */
@Getter
public enum ExceptionCodeEnum {

    SUCCESS(200, "操作成功"),
    FAILED(201, "操作失败"),
    VALIDATE_FAILED(202, "参数校验失败"),
    NOT_VALIDATED(203, "接口未带验证"),
    NO_REPEATED(204, "请勿重复提交"),
    OPERATE_FAILED(301, "redis操作失败"),
    NOT_AUTHORIZED(401, "未经授权"),
    FORBIDDEN(403, "权限禁止"),
    ERROR(500, "未知错误");

    private int code;
    private String msg;

    ExceptionCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
