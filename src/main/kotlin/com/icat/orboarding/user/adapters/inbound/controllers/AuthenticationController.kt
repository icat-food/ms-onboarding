package com.icat.orboarding.user.adapters.inbound.controllers

import com.icat.orboarding.user.adapters.inbound.dtos.request.LoginDTO
import com.icat.orboarding.user.adapters.inbound.dtos.response.TokenDTO
import com.icat.orboarding.user.application.ports.inbound.AuthenticationServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/auth")
class AuthenticationController(
    @Autowired val authenticationServicePort: AuthenticationServicePort
    ) {

    @PostMapping
    fun login(@RequestBody @Validated loginDTO: LoginDTO): ResponseEntity<TokenDTO> {
        return ResponseEntity.ok(authenticationServicePort.authenticate(loginDTO))
    }
}