package com.shanezhou.pro.component;

import com.shanezhou.pro.entity.AccountVO;
import com.shanezhou.pro.entity.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt 增加内容
 * @author ShaneZhou
 * @since 2020/10/10 周六
 */
//@Component
public class JwtTokenEnhancer extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        SecurityUser user = (SecurityUser)authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>(1);
        info.put("userInfo", user);
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return super.enhance(accessToken, authentication);
    }
}
