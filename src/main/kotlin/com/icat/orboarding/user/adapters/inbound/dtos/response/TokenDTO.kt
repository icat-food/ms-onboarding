package com.icat.orboarding.user.adapters.inbound.dtos.response

import com.icat.orboarding.user.application.domain.TokenDomain

data class TokenDTO(var token: String) {
    init {
        this.token = "Bearer $token"
    }
}

fun TokenDomain.toTokenDTO(): TokenDTO =
    TokenDTO(token)