package com.icat.orboarding.user.adapters.outbound.persistence.mysql

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.toRestaurantEntity
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.toRestaurantDomain
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories.RestaurantRepository
import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.ports.outbound.RestaurantPersistencePort
import org.springframework.stereotype.Component

@Component
class RestaurantPersistence(private val restaurantRepository: RestaurantRepository) : RestaurantPersistencePort {

    override fun cnpjAlreadyRegistered(cnpj: String): Boolean =
        restaurantRepository.existsByCnpj(cnpj)

    override fun createRestaurant(restaurantDomain: RestaurantDomain): RestaurantDomain =
        restaurantRepository.save(restaurantDomain.toRestaurantEntity()).toRestaurantDomain()
}
