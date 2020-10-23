//package com.shanezhou.pro.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author ShaneZhou
// * @since 2020/10/9 周五
// */
//@Configuration
//public class GatewayConfig {
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes().route("route1",
//                r -> r.path("/account/accounts")
//                            .uri("http://localhost:8001/account/accounts"))
//                .build();
//    }
//}
