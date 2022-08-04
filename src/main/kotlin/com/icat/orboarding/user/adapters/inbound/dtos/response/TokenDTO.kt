package com.icat.orboarding.user.adapters.inbound.dtos.response

import com.icat.orboarding.user.application.domain.TokenDomain

}

fun TokenDomain.toTokenDTO(): TokenDTO =
    TokenDTO(token)