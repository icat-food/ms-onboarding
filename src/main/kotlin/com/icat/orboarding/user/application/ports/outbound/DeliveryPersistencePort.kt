package com.icat.orboarding.user.application.ports.outbound

import com.icat.orboarding.user.application.domain.DeliveryDomain

interface DeliveryPersistencePort {
    fun isAlreadyRegistered(cpf: String): Boolean
    fun createDelivery(deliveryDomain: DeliveryDomain): DeliveryDomain
}