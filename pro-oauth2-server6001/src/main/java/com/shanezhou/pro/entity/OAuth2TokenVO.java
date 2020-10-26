package com.shanezhou.pro.entity;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Map;
import java.util.Set;

/**
 * @author ShaneZhou
 * @since 2020/10/26 周一 17:01:37
 */
public class OAuth2TokenVO {

    private String accessToken;

    private String tokenType;

    private String refreshToken;

    private Integer expiresIn;

    private Set<String> scope;

    private Map<String, Object> infoMap;

    private String tokenHead = "Bearer ";

    public OAuth2TokenVO(OAuth2AccessToken oAuth2AccessToken) {
        this.accessToken = oAuth2AccessToken.getValue();
        this.tokenType = oAuth2AccessToken.getTokenType();
        this.refreshToken = oAuth2AccessToken.getRefreshToken().getValue();
        this.expiresIn = oAuth2AccessToken.getExpiresIn();
        this.scope = oAuth2AccessToken.getScope();
        this.infoMap = oAuth2AccessToken.getAdditionalInformation();

    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public Map<String, Object> getInfoMap() {
        return infoMap;
    }

    public void setInfoMap(Map<String, Object> infoMap) {
        this.infoMap = infoMap;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }
}
