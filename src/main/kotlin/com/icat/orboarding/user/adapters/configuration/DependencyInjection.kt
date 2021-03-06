package com.icat.orboarding.user.adapters.configuration

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.RestaurantPersistence
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.UserPersistence
import com.icat.orboarding.user.application.services.RestaurantService
import com.icat.orboarding.user.application.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjection {

    @Bean
    fun userService(userPersistence: UserPersistence) =
        UserService(userPersistence)

    @Bean
    fun restaurantService(restaurantPersistence: RestaurantPersistence, userService: UserService) =
        RestaurantService(restaurantPersistence, userService)
}
