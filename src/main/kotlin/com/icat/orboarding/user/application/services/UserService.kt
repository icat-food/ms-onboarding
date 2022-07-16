package com.icat.orboarding.user.application.services

import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.exceptions.EmailAlreadyRegisteredException
import com.icat.orboarding.user.application.exceptions.UserNotFoundException
import com.icat.orboarding.user.application.ports.inbound.UserServicePort
import com.icat.orboarding.user.application.ports.outbound.UserPersistencePort

class UserService(private val userPersistencePort: UserPersistencePort) : UserServicePort {

    override fun createUser(userDomain: UserDomain): UserDomain {
        if (userPersistencePort.emailAlreadyRegistered(userDomain.email)) {
            throw EmailAlreadyRegisteredException("The email ${userDomain.email} is already registered")
        }
//      TODO criptografar a senha
        return userPersistencePort.createUser(userDomain)
    }

    override fun getUser(email: String): UserDomain =
        userPersistencePort.getUser(email)
            .map { it }
            .orElseThrow { throw UserNotFoundException("User with email $email not found") }

    override fun updateUser(email: String, userDomainToUpdate: UserDomain): UserDomain {
        if (userPersistencePort.emailAlreadyRegistered(userDomainToUpdate.email)) {
            throw EmailAlreadyRegisteredException("The email ${userDomainToUpdate.email} is already registered")
        }

        val currentUser = getUser(email)
        val userToUpdate = currentUser.copy(
            email = userDomainToUpdate.email,
            password = userDomainToUpdate.password
        )

        return userPersistencePort.updateUser(userToUpdate)
    }
}
