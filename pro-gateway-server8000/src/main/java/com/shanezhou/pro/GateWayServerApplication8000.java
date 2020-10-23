package com.shanezhou.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ShaneZhou
 * @since 2020/10/9 周五
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateWayServerApplication8000 {

    public static void main(String[] args) {
        SpringApplication.run(GateWayServerApplication8000.class, args);
    }
}
