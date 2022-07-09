package com.icat.orboarding.user.adapters.inbound.dtos.response

data class TokenDTO(var token: String) {
    init {
        this.token = "Bearer $token"
    }
}
