package com.icat.orboarding.user.adapters.outbound.persistence.mysql

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.UserEntity
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories.UserRepository
import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.ports.outbound.UserPersistencePort
import org.springframework.stereotype.Component
import java.util.*


@Component
class UserPersistence(private val userRepository: UserRepository) : UserPersistencePort {

    override fun emailAlreadyRegistered(email: String): Boolean =
        userRepository.existsByEmailContainsIgnoreCase(email)

    override fun createUser(userDomain: UserDomain): UserDomain {
        return userRepository.save(
            UserEntity(
                email = userDomain.email,
                // TODO encrypt the password
                password = userDomain.password!!
            )
        ).toUserDomain()
    }

    override fun getUser(email: String): Optional<UserDomain> =
        userRepository.findByEmailIgnoreCase(email)
            .map { it.toUserDomain() }
            .or { Optional.empty() }

    override fun getUserByEmail(email: String): UserEntity =
        userRepository.findByEmailIgnoreCase(email).get()

    private fun UserEntity.toUserDomain(): UserDomain =
        UserDomain(id, email, password)
}
