package com.shanezhou.pro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author ShaneZhou
 * @since 2020/10/10 周六
 */
@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;
}
