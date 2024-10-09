package com.example.usermanagementservice.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtUtil {

    private val SECRET_KEY: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    private val EXPIRATION_TIME = 1000*60 // 10 hours


    fun generateToken(subject: String): String {
        println("user is : $subject")
        return Jwts.builder()
            .setSubject(subject)
            .setIssuedAt(Date()) // Set the current date/time as issue time
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set token expiration
            .signWith(SECRET_KEY) // Sign the token using the secret key and HS256 algorithm
            .compact()
    }

    fun validateToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY) // Set the secret key for signature validation
            .build()
            .parseClaimsJws(token) // Parse the token to extract claims
            .body
    }

    fun isTokenExpired(token: String): Boolean {
        val claims = validateToken(token)
        return claims.expiration.before(Date())
    }

    fun getSubjectFromToken(token: String): String {
        val claims = validateToken(token)
        return claims.subject
    }

    fun isValidToken(token: String): Boolean {
        return try {
            // Check if the token is valid
            !isTokenExpired(token) // Ensure the token is not expired
        } catch (e: Exception) {
            false // Return false if any exception occurs (invalid or expired token)
        }
    }
}
