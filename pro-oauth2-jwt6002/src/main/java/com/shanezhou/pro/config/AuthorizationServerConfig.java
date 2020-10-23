package com.shanezhou.pro.config;

import com.shanezhou.pro.component.JwtTokenEnhancer;
import com.shanezhou.pro.service.impl.AuthDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务器配置
 * @author ShaneZhou
 * @since 2020/10/9 周五
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthDetailServiceImpl authDetailServiceImpl;

    @Autowired
    //@Qualifier("redisTokenStore")   // 使用 redis 存储token
    @Qualifier("jwtTokenStore")   // 使用 jwt 存储token
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancer = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);    // 配置JWT 的内容增强
        delegates.add(jwtAccessTokenConverter);
        tokenEnhancer.setTokenEnhancers(delegates);

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(authDetailServiceImpl)
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancer);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client-shane")
                .secret(passwordEncoder.encode("secret-shane"))
                .accessTokenValiditySeconds(60 * 60)    // token的有效期
                .refreshTokenValiditySeconds(60 * 60 * 24)
                //.redirectUris("https://www.baidu.com")  // 配置redirect_uri，用于授权成功后跳转
                .redirectUris("http://localhost:6002/login")  // 单点登录时配置
                .autoApprove(true)  // 自动授权配置
                .scopes("all")  // 配置申请范围
                .authorizedGrantTypes("password", "authorization_code", "refresh_token");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");   // 获取密钥需要身份认证，使用单点登录时必须配置
    }
}
