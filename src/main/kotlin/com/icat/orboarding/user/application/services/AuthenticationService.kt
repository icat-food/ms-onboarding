package com.icat.orboarding.user.application.services

import com.icat.orboarding.user.application.ports.outbound.UserPersistencePort
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class AuthenticationService(
    private val repository: UserPersistencePort) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return repository.getUserByEmail(username)
    }
}