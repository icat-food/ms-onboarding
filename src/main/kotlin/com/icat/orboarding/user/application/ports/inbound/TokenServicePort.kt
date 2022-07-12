package com.icat.orboarding.user.application.ports.inbound

import org.springframework.security.core.Authentication

interface TokenServicePort {

    fun generateToken(authentication: Authentication): String
    fun isTokenValid(token: String?): Boolean
    fun getTokenId(token: String?): String?
}