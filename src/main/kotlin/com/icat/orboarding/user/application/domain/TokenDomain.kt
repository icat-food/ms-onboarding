package com.icat.orboarding.user.application.domain

data class TokenDomain(var token: String) {
    init {
        this.token = "Bearer $token"
    }
}
