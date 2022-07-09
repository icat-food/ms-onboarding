package com.icat.orboarding.user.application.services

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import java.util.*

class TokenService {
    @Value("\${jwt.expiration}")
    private val expiration: String? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(authentication: Authentication): String {
        val user: User = authentication.principal as User
        val now = Date()
        val exp = Date(now.time + expiration!!.toLong())

        return Jwts.builder().setIssuer("ms-onboarding")
            .setSubject(user.username)
            .setIssuedAt(Date())
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS256, secret).compact()
    }

    fun isTokenValid(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getTokenId(token: String?): String? {
        val body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        return body.subject
    }
}