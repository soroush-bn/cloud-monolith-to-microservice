package com.cloud.user.service.utils

import com.cloud.user.service.exceptions.JwtTokenMalformedException
import com.cloud.user.service.exceptions.JwtTokenMissingException
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {
    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    @Value("\${jwt.token.validity}")
    private val tokenValidity: Long = 0
    fun getClaims(token: String?): io.jsonwebtoken.Claims? {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody()
        } catch (e: Exception) {
            println(e.message + " => " + e)
        }
        return null
    }

    fun generateToken(id: String?): String {
        val claims: io.jsonwebtoken.Claims = io.jsonwebtoken.Jwts.claims().setSubject(id)
        val nowMillis = System.currentTimeMillis()
        val expMillis = nowMillis + tokenValidity
        val exp = Date(expMillis)
        return io.jsonwebtoken.Jwts.builder().setClaims(claims).setIssuedAt(Date(nowMillis)).setExpiration(exp)
            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, jwtSecret).compact()
    }

    @Throws(
        JwtTokenMalformedException::class,
        JwtTokenMissingException::class
    )
    fun validateToken(token: String?) {
        try {
            io.jsonwebtoken.Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
        } catch (ex: io.jsonwebtoken.SignatureException) {
            throw JwtTokenMalformedException("Invalid JWT signature")
        } catch (ex: io.jsonwebtoken.MalformedJwtException) {
            throw JwtTokenMalformedException("Invalid JWT token")
        } catch (ex: io.jsonwebtoken.ExpiredJwtException) {
            throw JwtTokenMalformedException("Expired JWT token")
        } catch (ex: io.jsonwebtoken.UnsupportedJwtException) {
            throw JwtTokenMalformedException("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            throw JwtTokenMissingException("JWT claims string is empty.")
        }
    }
}