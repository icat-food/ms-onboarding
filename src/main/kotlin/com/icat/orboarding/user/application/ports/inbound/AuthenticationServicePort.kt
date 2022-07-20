package com.icat.orboarding.user.application.ports.inbound

import com.icat.orboarding.user.adapters.inbound.dtos.request.LoginDTO
import com.icat.orboarding.user.application.domain.TokenDomain
import org.springframework.security.core.userdetails.UserDetailsService

interface AuthenticationServicePort : UserDetailsService {

    fun authenticate(loginDTO: LoginDTO): TokenDomain
}