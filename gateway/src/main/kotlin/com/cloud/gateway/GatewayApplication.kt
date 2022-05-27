package com.cloud.gateway

import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.List
import java.util.function.Predicate
import javax.naming.AuthenticationException

@SpringBootApplication
@EnableEurekaClient
class GatewayApplication

fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}

@Component
class JwtAuthenticationFilter : GatewayFilter {
	@Autowired
	private val jwtUtil: JwtUtil? = null
	override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
		val request = exchange.request
		val apiEndpoints = List.of("/register", "/login")
		val isApiSecured =
			Predicate { r: ServerHttpRequest ->
				apiEndpoints.stream()
					.noneMatch { uri: String? ->
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
				jwtUtil!!.validateToken(token)
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

@Component
class JwtUtil {
	@Value("\${jwt.secret}")
	private val jwtSecret: String? = null
	fun getClaims(token: String?): Claims? {
		try {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
		} catch (e: Exception) {
			println(e.message + " => " + e)
		}
		return null
	}

	@Throws(JwtTokenMalformedException::class, JwtTokenMissingException::class)
	fun validateToken(token: String?) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
		} catch (ex: SignatureException) {
			throw JwtTokenMalformedException("Invalid JWT signature")
		} catch (ex: MalformedJwtException) {
			throw JwtTokenMalformedException("Invalid JWT token")
		} catch (ex: ExpiredJwtException) {
			throw JwtTokenMalformedException("Expired JWT token")
		} catch (ex: UnsupportedJwtException) {
			throw JwtTokenMalformedException("Unsupported JWT token")
		} catch (ex: IllegalArgumentException) {
			throw JwtTokenMissingException("JWT claims string is empty.")
		}
	}
}

class JwtTokenMalformedException(msg: String?) : AuthenticationException(msg) {
	companion object {
		private const val serialVersionUID = 1L
	}
}

class JwtTokenMissingException(msg: String?) : AuthenticationException(msg) {
	companion object {
		private const val serialVersionUID = 1L
	}
}