package com.shanezhou.pro.controller;

import com.shanezhou.pro.component.LoginHandler;
import com.shanezhou.pro.component.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShaneZhou
 * @since 2020/10/12 周一
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginHandler loginHandler;

    @GetMapping("/currentUser")
    public UserDTO getUser() {
        return loginHandler.getCurrentUser();
    }

}
