package com.icat.orboarding.user.application.services

import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.exceptions.EmailAlreadyRegisteredException
import com.icat.orboarding.user.application.exceptions.UserNotFoundException
import com.icat.orboarding.user.application.ports.inbound.UserServicePort
import com.icat.orboarding.user.application.ports.outbound.UserPersistencePort

class UserService(private val userPersistencePort: UserPersistencePort) : UserServicePort {

    override fun createUser(userDomain: UserDomain): UserDomain {
        if (userPersistencePort.emailAlreadyRegistered(userDomain.email)) {
            throw EmailAlreadyRegisteredException("The email ${userDomain.email} already registered")
        }

        return userPersistencePort.createUser(userDomain).removePassword()
    }

    override fun getUser(email: String): UserDomain =
        userPersistencePort.getUser(email)
            .map { it }
            .orElseThrow { throw UserNotFoundException("User with email $email not found") }

    private fun UserDomain.removePassword(): UserDomain =
        copy(password = null)
}
