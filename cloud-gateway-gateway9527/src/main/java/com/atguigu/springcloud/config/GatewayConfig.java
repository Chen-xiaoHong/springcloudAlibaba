package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder locatorBuilder){
        RouteLocatorBuilder.Builder routes = locatorBuilder.routes();
        routes.route("baidu.guonei_routh1",r -> r.path("/guonei").uri("https://news.baidu.com/guonei"));
        routes.route("baidu.guonei_routh2",r -> r.path("/guoji").uri("https://news.baidu.com/guoji"));
        routes.route("baidu.guonei_routh3",r -> r.path("/news").uri("https://news.baidu.com"));
        return routes.build();
    }
}
