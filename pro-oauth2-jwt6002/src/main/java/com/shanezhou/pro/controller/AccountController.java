package com.shanezhou.pro.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author ShaneZhou
 * @since 2020/10/9 周五
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/test")
    public Object test(Authorization authorization, HttpServletRequest request) {
        String head = request.getHeader("Authorization");
        String token = head.substring("bearer".length() + 1);
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey("test_key".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
        return claimsJws;
    }

    @GetMapping("/test2")
    public Object test2(Authorization authorization, HttpServletRequest request) {
        String head = request.getHeader("Authorization");
        String token = head.substring("bearer".length() + 1);
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey("test_key".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
        return claimsJws;
    }

}
