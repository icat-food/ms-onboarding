package com.icat.orboarding.user.application.ports.inbound

import org.springframework.security.core.userdetails.UserDetailsService

interface AuthenticationServicePort : UserDetailsService {
}