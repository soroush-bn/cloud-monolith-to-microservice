package com.cloud.user.service.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {
    @Value(
        "\${" +
                "jwt.secret}"
    )
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
        val claims: io.jsonwebtoken.Claims = Jwts.claims().setSubject(id)
        val nowMillis = System.currentTimeMillis()
        val expMillis = nowMillis + tokenValidity
        val exp = Date(expMillis)
        return Jwts.builder().setClaims(claims).setIssuedAt(Date(nowMillis)).setExpiration(exp)
            .signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    }
}