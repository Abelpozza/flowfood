package com.flowfood.restaurant.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {
    private val secretKey: SecretKey =
        Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun generatedToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
        .setExpiration(
            Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)
        )
            .signWith(secretKey)
            .compact()
    }
    fun extractEmail(token: String): String {
        return extractAllClaims(token)
            .subject
    }
    fun isTokenValid(token: String): Boolean {
        return try {
            extractAllClaims(token)
            true
        } catch (e: Exception) {
            false
        }
    }
    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }
}