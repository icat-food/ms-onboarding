package com.icat.orboarding.user.application.ports.inbound

import com.icat.orboarding.user.application.domain.UserDomain

interface UserServicePort {
    fun createUser(userDomain: UserDomain): UserDomain
}
