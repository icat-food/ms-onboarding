package com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.RestaurantEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository : JpaRepository<RestaurantEntity, String> {
    fun existsByCnpj(cnpj: String): Boolean
}
