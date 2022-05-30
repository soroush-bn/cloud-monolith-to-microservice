package com.cloud.gateway.jwt

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.function.Predicate

@Component
class JwtAuthenticationFilter(private val jwtUtil: JwtUtil) : GatewayFilter {
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val request = exchange.request
        val apiEndpoints = listOf("/register", "/login")
        val isApiSecured = Predicate { r: ServerHttpRequest ->
            apiEndpoints.stream().noneMatch { uri: String? ->
                r.uri.path.contains(uri!!)
            }
        }
        if (isApiSecured.test(request)) {
            if (!request.headers.containsKey("Authorization")) {
                val response = exchange.response
                response.statusCode = HttpStatus.UNAUTHORIZED
                return response.setComplete()
            }
            val token = request.headers.getOrEmpty("Authorization")[0]
            try {
                jwtUtil.validateToken(token)
            } catch (e: JwtTokenMalformedException) {
                // e.printStackTrace();
                val response = exchange.response
                response.statusCode = HttpStatus.BAD_REQUEST
                return response.setComplete()
            } catch (e: JwtTokenMissingException) {
                val response = exchange.response
                response.statusCode = HttpStatus.BAD_REQUEST
                return response.setComplete()
            }
            val claims = jwtUtil.getClaims(token)
            exchange.request.mutate().header("id", claims!!["id"].toString()).build()
        }
        return chain.filter(exchange)
    }
}