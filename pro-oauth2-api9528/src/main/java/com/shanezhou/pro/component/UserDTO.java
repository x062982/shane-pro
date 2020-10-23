package com.shanezhou.pro.component;

import java.util.List;

/**
 * @author ShaneZhou
 * @since 2020/10/12 周一
 */
public class UserDTO {

    private Long id;
    private String username;
    private byte[] password;
    private List<String> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
}
