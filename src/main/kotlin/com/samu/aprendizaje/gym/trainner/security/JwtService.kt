package com.samu.aprendizaje.gym.trainner.security

import com.samu.aprendizaje.gym.trainner.models.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.Date

@Service
class JwtService {

    private val jwtSecret = "k7Q92xMTbA4XvFzEhgUq9g5Jr8wLt1zNkHy3BvUwPeL="
    private val key = Keys.hmacShaKeyFor(jwtSecret.toByteArray(StandardCharsets.UTF_8))


    fun generateToken(user: User): String {
         val claims = Jwts.claims().setSubject(user.username)
        claims["id"] = user.id
        claims["name"] = user.name
        claims["email"] = user.email
        claims["username"] = user.username
        claims["currentWeights"] = user.currentWeights
        claims["goalWeights"] = user.goalWeights
        claims["dateOfBirth"] = user.dateOfBirth

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 100 * 60 * 60 * 48   )) // 48 hour expiration
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun extractUsername(token: String): String =
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
}