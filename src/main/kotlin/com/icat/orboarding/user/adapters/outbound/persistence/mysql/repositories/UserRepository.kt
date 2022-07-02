package com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, String> {
    fun existsByEmailContainsIgnoreCase(email: String): Boolean
}
