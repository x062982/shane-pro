package com.shanezhou.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author ShaneZhou
 * @since 2020/9/29 周二
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AccountServerApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(AccountServerApplication8001.class, args);
    }
}
