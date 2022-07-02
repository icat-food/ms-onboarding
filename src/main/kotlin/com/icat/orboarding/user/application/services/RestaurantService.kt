package com.icat.orboarding.user.application.services

import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.exceptions.CnpjAlreadyRegisteredException
import com.icat.orboarding.user.application.ports.inbound.RestaurantServicePort
import com.icat.orboarding.user.application.ports.inbound.UserServicePort
import com.icat.orboarding.user.application.ports.outbound.RestaurantPersistencePort
import java.util.UUID

class RestaurantService(
    private val restaurantPersistencePort: RestaurantPersistencePort,
    private val userServicePort: UserServicePort
) : RestaurantServicePort {

    override fun createRestaurant(restaurantDomain: RestaurantDomain): RestaurantDomain {
        if (restaurantPersistencePort.cnpjAlreadyRegistered(restaurantDomain.cnpj)) {
            throw CnpjAlreadyRegisteredException("The cnpj ${restaurantDomain.cnpj} already registered")
        }

        val createdUser = userServicePort.createUser(restaurantDomain.userDomain!!)

//      TODO criar a url da imagem
        return restaurantPersistencePort.createRestaurant(restaurantDomain.copy(imageUrl = "http://aws/${UUID.randomUUID()}", userDomain = createdUser))
    }
}
