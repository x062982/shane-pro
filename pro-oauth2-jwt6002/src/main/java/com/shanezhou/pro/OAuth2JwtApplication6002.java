package com.shanezhou.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ShaneZhou
 * @since 2020/10/9 周五
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OAuth2JwtApplication6002 {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2JwtApplication6002.class, args);
    }
}
