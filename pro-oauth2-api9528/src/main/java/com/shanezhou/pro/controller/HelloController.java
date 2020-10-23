package com.shanezhou.pro.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ShaneZhou
 * @since 2020/10/12 周一
 */
@RestController
public class HelloController {

    @GetMapping("/test")
    public String test() {
        return "Hello Test";
    }

    @GetMapping("/token")
    public String getToken(HttpServletRequest request) {
        String user = request.getHeader("user");
        return user;
    }
}
