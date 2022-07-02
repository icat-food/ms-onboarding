package com.icat.orboarding.user.adapters.outbound.persistence.mysql

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.RestaurantEntity
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.UserEntity
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories.RestaurantRepository
import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.ports.outbound.RestaurantPersistencePort
import org.springframework.stereotype.Component

@Component
class RestaurantPersistence(private val restaurantRepository: RestaurantRepository) : RestaurantPersistencePort {

    override fun cnpjAlreadyRegistered(cnpj: String): Boolean =
        restaurantRepository.existsByCnpj(cnpj)

    override fun createRestaurant(restaurantDomain: RestaurantDomain): RestaurantDomain =
        restaurantRepository.save(
            RestaurantEntity(
                name = restaurantDomain.name,
                cnpj = restaurantDomain.cnpj,
                imageUrl = restaurantDomain.imageUrl!!,
                userEntity = UserEntity(
                    restaurantDomain.userDomain!!.id!!,
                    restaurantDomain.userDomain.email,
                    restaurantDomain.userDomain.password!!
                )
            )
        ).toRestaurantDomain()

    private fun RestaurantEntity.toRestaurantDomain(): RestaurantDomain =
        RestaurantDomain(
            id = id,
            name = name,
            cnpj = cnpj,
            imageUrl = imageUrl,
            userDomain = UserDomain(
                id = userEntity!!.id,
                email = userEntity.email
            )
        )
}
