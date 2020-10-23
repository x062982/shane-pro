package com.shanezhou.pro.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author ShaneZhou
 * @since 2020/10/9 周五
 */
@Configuration
public class RedisRateLimiterConfig {

    /**
     * 根据ip地址限流
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     * 根据查询username 限流
     * @return
     */
    //@Bean
    //public KeyResolver userKeyResolver() {
    //    return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("username"));
    //}
}
