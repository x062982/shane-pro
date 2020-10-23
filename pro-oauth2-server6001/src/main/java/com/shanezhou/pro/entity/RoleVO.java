package com.shanezhou.pro.entity;

import lombok.Data;

/**
 * @author ShaneZhou
 * @since 2020/9/30 周三
 */
@Data
public class RoleVO {

    private Long id;

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色名
     */
    private String roleName;
}
