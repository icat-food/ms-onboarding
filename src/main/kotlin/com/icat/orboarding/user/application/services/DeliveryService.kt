package com.icat.orboarding.user.application.services

import com.icat.orboarding.user.application.domain.DeliveryDomain
import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.exceptions.CpfAlreadyRegisteredException
import com.icat.orboarding.user.application.ports.inbound.DeliveryServicePort
import com.icat.orboarding.user.application.ports.inbound.UserServicePort
import com.icat.orboarding.user.application.ports.outbound.DeliveryPersistencePort
import java.util.*

class DeliveryService(
    private val deliveryPersistence: DeliveryPersistencePort,
    private val userService: UserServicePort
): DeliveryServicePort {

    override fun createDelivery(deliveryDomain: DeliveryDomain): DeliveryDomain {
        if (deliveryPersistence.isAlreadyRegistered(deliveryDomain.cpf)){
            throw CpfAlreadyRegisteredException("The CPF ${deliveryDomain.cpf} is already registered")
        }

        val createdUser: UserDomain = userService.createUser(deliveryDomain.userDomain!!)
        // TODO implementar o envio e geração de URL da imagem
        val updatedDeliveryDomain = deliveryDomain.copy(imageUrl = UUID.randomUUID().toString(), userDomain = createdUser)
        return deliveryPersistence.createDelivery(updatedDeliveryDomain)
    }
}