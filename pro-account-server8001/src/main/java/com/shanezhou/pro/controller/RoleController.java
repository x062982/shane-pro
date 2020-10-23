package com.shanezhou.pro.controller;

import com.shanezhou.pro.entity.RoleEntity;
import com.shanezhou.pro.entity.vo.RoleVO;
import com.shanezhou.pro.exception.APIException;
import com.shanezhou.pro.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShaneZhou
 * @since 2020/9/30 周三
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/role/{id}")
    public RoleVO getRoleById(@PathVariable("id") Long id) {
        RoleEntity roleEntity = roleService.getById(id);
        if (roleEntity == null) {
            throw new APIException(id + "不存在");
        }
        return convertRoleVO(roleEntity);
    }

    private RoleVO convertRoleVO(RoleEntity entity) {
        RoleVO vo = new RoleVO();
        if (entity != null) {
            vo.setId(entity.getId());
            vo.setRoleCode(entity.getRoleCode());
            vo.setRoleName(entity.getRoleName());
        }
        return vo;
    }


}
