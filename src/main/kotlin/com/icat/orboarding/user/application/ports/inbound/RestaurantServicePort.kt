package com.icat.orboarding.user.application.ports.inbound

import com.icat.orboarding.user.application.domain.RestaurantDomain

interface RestaurantServicePort {
    fun createRestaurant(restaurantDomain: RestaurantDomain): RestaurantDomain
}
