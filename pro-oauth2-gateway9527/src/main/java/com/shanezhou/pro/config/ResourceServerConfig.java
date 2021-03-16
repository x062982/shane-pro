package com.shanezhou.pro.config;

import cn.hutool.core.util.ArrayUtil;
import com.shanezhou.pro.component.AuthorizationManager;
import com.shanezhou.pro.component.IgnoreUrlsConfig;
import com.shanezhou.pro.component.RestAuthenticationEntryPoint;
import com.shanezhou.pro.component.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 资源服务器配置
 * @author ShaneZhou
 * @since 2020/10/12 周一
 */
@EnableWebFluxSecurity
@Configuration
public class ResourceServerConfig {

    @Autowired
    private AuthorizationManager authorizationManager;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security) {
        security.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        // 自定义处理JWT请求头过期或签名错误的结果
        security.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint);

        security.authorizeExchange()
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(),
                        String.class))
                .permitAll()
                .anyExchange().access(authorizationManager) // 鉴权管理器
                .and()
                .exceptionHandling()
                //.accessDeniedHandler(restfulAccessDeniedHandler)    // 处理未授权
                //.authenticationEntryPoint(restAuthenticationEntryPoint) // 处理未认证
                .and()
                .csrf().disable();
        return security.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix("ROLE_");
        converter.setAuthoritiesClaimName("authorities");
        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
        return new ReactiveJwtAuthenticationConverterAdapter(authenticationConverter);
    }


}
