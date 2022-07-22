package com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.DeliveryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DeliveryRepository: JpaRepository<DeliveryEntity, String> {
    fun existsByCpf(cpf: String): Boolean
}