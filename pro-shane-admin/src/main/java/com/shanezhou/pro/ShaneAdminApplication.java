package com.shanezhou.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ShaneZhou
 * @since 2020/10/15 周四 9:56:28
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ShaneAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShaneAdminApplication.class, args);
    }
}
