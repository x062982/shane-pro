package com.shanezhou.pro.controller;

import com.shanezhou.pro.entity.OAuth2TokenVO;
import com.shanezhou.pro.entity.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShaneZhou
 * @since 2020/10/26 周一 17:21:50
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    public ResultVO<OAuth2TokenVO> postAccessToken(Principal principal, @RequestParam Map<String, String> map) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken  = tokenEndpoint
                .postAccessToken(principal, map).getBody();

        OAuth2TokenVO tokenVO = new OAuth2TokenVO(oAuth2AccessToken);
        return new ResultVO<>(200, "成功" , tokenVO);
    }
}
