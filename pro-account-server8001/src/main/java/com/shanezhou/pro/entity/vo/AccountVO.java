package com.shanezhou.pro.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author ShaneZhou
 * @since 2020/9/24 周四
 */
@Data
public class AccountVO {

    /**
     * 编号
     */
    private Long id;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空，请输入用户名！")
    @Size(min = 2, message = "用户名最少输入2个字符！")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空，请输入密码！")
    @Size(min = 6, max = 16, message = "密码长度请保证在6位至16位之间")
    private char[] password;

    /**
     * 角色
     */
    private Long roleId;
}
