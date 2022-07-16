package com.icat.orboarding.user.adapters.outbound.persistence.mysql

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.toDeliveryDomain
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.toDeliveryEntity
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories.DeliveryRepository
import com.icat.orboarding.user.application.domain.DeliveryDomain
import com.icat.orboarding.user.application.ports.outbound.DeliveryPersistencePort
import org.springframework.stereotype.Component

@Component
class DeliveryPersistence(private val deliveryRepository: DeliveryRepository): DeliveryPersistencePort {
    override fun isAlreadyRegistered(cpf: String): Boolean =
        deliveryRepository.existsByCpf(cpf)

    override fun createDelivery(deliveryDomain: DeliveryDomain) =
        deliveryRepository.save(deliveryDomain.toDeliveryEntity()).toDeliveryDomain()
}