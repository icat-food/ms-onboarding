package com.icat.orboarding.user.application.services

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.UserEntity
import com.icat.orboarding.user.application.ports.inbound.TokenServicePort
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import java.util.*

class TokenService: TokenServicePort {
    @Value("\${jwt.expiration}")
    private val expiration: String? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    override fun generateToken(authentication: Authentication): String {
        val user: UserEntity = authentication.principal as UserEntity
        val now = Date()
        val exp = Date(now.time + expiration!!.toLong())

        return Jwts.builder().setIssuer("ms-onboarding")
            .setSubject(user.username)
            .setIssuedAt(Date())
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS256, secret).compact()
    }

    override fun isTokenValid(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun getTokenId(token: String?): String? {
        val body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        return body.subject
    }
}