package com.shanezhou.pro.component;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt内容增加
 * @author ShaneZhou
 * @since 2020/10/9 周五
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> enhance = new HashMap<>();
        enhance.put("key", authentication.getUserAuthentication().getAuthorities());
        enhance.put("enhance", "enhance info");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(enhance);
        return accessToken;
    }
}
