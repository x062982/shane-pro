package com.shanezhou.pro.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shanezhou
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {


    /**
     * ApiIdempotent 注释类型的枚举类
     */
    public enum ApiIdempotentEnum {
        /**
         * 检查
         */
        CHECK,

        /**
         * 创建
         */
        CREATE
    }

    ApiIdempotentEnum type() default ApiIdempotentEnum.CHECK;
}
