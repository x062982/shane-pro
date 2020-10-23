package com.shanezhou.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ShaneZhou
 * @since 2020/9/29 周二
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Oauth2ServerApplication6001 {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication6001.class, args);
    }
}
