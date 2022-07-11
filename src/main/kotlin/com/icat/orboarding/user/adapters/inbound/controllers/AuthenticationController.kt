package com.icat.orboarding.user.adapters.inbound.controllers

import com.icat.orboarding.user.adapters.inbound.dtos.request.LoginDTO
import com.icat.orboarding.user.adapters.inbound.dtos.response.TokenDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1")
class AuthenticationController(
    @Autowired val authenticationManager: AuthenticationManager,
    @Autowired val tokenService: com.icat.orboarding.user.application.services.TokenService
    ) {

    @PostMapping("/auth")
    fun auth(@RequestBody @Validated loginDTO: LoginDTO): ResponseEntity<TokenDTO?>? {
        val usernamePasswordAuthenticationToken =
            UsernamePasswordAuthenticationToken(loginDTO.email, loginDTO.password)
        val authentication: Authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        val token: String = tokenService.generateToken(authentication)
        return ResponseEntity.ok(TokenDTO(token = token))
    }
}