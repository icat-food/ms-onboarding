package com.icat.orboarding.user.application.ports.outbound

import com.icat.orboarding.user.application.domain.UserDomain

interface UserPersistencePort {
    fun emailAlreadyRegistered(email: String): Boolean
    fun createUser(userDomain: UserDomain): UserDomain
}
