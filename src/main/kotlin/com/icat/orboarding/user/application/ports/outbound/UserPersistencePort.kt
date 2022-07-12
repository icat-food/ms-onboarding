package com.icat.orboarding.user.application.ports.outbound

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.UserEntity
import com.icat.orboarding.user.application.domain.UserDomain
import java.util.Optional

interface UserPersistencePort {
    fun emailAlreadyRegistered(email: String): Boolean
    fun createUser(userDomain: UserDomain): UserDomain
    fun getUser(email: String): Optional<UserDomain>
    fun updateUser(userDomainToUpdate: UserDomain): UserDomain
    fun getUserByEmail(email: String): UserEntity
}
