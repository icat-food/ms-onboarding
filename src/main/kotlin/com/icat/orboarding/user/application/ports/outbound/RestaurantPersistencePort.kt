package com.icat.orboarding.user.application.ports.outbound

import com.icat.orboarding.user.application.domain.RestaurantDomain

interface RestaurantPersistencePort {
    fun cnpjAlreadyRegistered(cnpj: String): Boolean
    fun createRestaurant(restaurantDomain: RestaurantDomain): RestaurantDomain
}
