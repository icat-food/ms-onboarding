package com.icat.orboarding.user.application.services

import com.icat.orboarding.user.adapters.inbound.dtos.request.LoginDTO
import com.icat.orboarding.user.adapters.inbound.dtos.response.TokenDTO
import com.icat.orboarding.user.application.ports.inbound.AuthenticationServicePort
import com.icat.orboarding.user.application.ports.outbound.UserPersistencePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.context.annotation.Lazy

class AuthenticationService(
    private val repository: UserPersistencePort,
    @Autowired val tokenService: TokenService
    ) : AuthenticationServicePort {

    @Autowired
    @Lazy
    private lateinit var authenticationManager: AuthenticationManager
    override fun authenticate(loginDTO: LoginDTO): TokenDTO {
        val authentication: Authentication = getAuthentication(loginDTO)
        return TokenDTO(token = tokenService.generateToken(authentication))
    }

    private fun getAuthentication(loginDTO: LoginDTO): Authentication {
        val usernamePasswordAuthenticationToken =
            UsernamePasswordAuthenticationToken(loginDTO.email, loginDTO.password)
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return repository.getUserByEmail(username)
    }


}