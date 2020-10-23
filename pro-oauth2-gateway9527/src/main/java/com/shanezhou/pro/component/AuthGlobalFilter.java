package com.shanezhou.pro.component;

import cn.hutool.core.codec.Base64;
import com.nimbusds.jose.JWSObject;
import com.shanezhou.pro.annotation.ApiIdempotent;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.HandlerInterceptor;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.Random;
import java.util.UUID;

/**
 * 将解析token放置header中    全局过滤
 * @author ShaneZhou
 * @since 2020/10/12 周一
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered, MethodInterceptor {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isNotEmpty(authorization)) {
            try {
                String token = authorization.replace("Bearer ", "");
                JWSObject jwsObject = JWSObject.parse(token);
                String userStr = jwsObject.getPayload().toString();
                ServerHttpRequest request = exchange.getRequest().mutate()
                        .header("user", userStr).build();
                exchange = exchange.mutate().request(request).build();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        ApiIdempotent annotation = methodInvocation.getMethod().getAnnotation(ApiIdempotent.class);
        System.out.println(annotation);
        return true;
    }
}
