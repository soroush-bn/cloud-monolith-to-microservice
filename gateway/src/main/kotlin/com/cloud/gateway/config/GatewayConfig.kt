package com.cloud.gateway.config

import com.cloud.gateway.jwt.JwtAuthenticationFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class GatewayConfig(private val jwtAuthenticationFilter: JwtAuthenticationFilter) {

    @Bean
    fun routes(builder: RouteLocatorBuilder): RouteLocator? {
        return builder.routes().route(
            "user-service"
        ) { r: PredicateSpec ->
            r.path("/user/**")
                .uri("http://user-service:9002")
        }
            .route(
                "comment-service"
            ) { r: PredicateSpec ->
                r.path("/comment/**").filters { f: GatewayFilterSpec ->
                    f.filter(
                        jwtAuthenticationFilter
                    )
                }.uri("http://comment-service:9001")
            }
            .route(
                "article-service"
            ) { r: PredicateSpec ->
                r.path("/article/**").filters { f: GatewayFilterSpec ->
                    f.filter(
                        jwtAuthenticationFilter
                    )
                }.uri("http://article-service:9003")
            }.build()
    }
}
// use code below to run without docker
//.uri("lb://USER-SERVICE")