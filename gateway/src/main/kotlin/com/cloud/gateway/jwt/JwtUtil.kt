package com.cloud.gateway.jwt

import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

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