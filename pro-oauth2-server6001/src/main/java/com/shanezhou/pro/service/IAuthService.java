package com.shanezhou.pro.service;

import com.shanezhou.pro.entity.AccountVO;
import com.shanezhou.pro.entity.RoleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ShaneZhou
 * @since 2020/9/30 周三
 */
@FeignClient("account-server")
@Service("authService")
public interface IAuthService {

    @GetMapping("/account/username")
    AccountVO getAccountByName(@RequestParam("username") String username);

    @GetMapping("/role/{id}")
    RoleVO getRoleById(@PathVariable("id") Long id);
}
