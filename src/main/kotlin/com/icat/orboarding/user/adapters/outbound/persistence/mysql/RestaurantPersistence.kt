package com.icat.orboarding.user.adapters.outbound.persistence.mysql

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.RestaurantEntity
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories.RestaurantRepository
import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.ports.outbound.RestaurantPersistencePort
import org.springframework.stereotype.Component

@Component
class RestaurantPersistence(private val restaurantRepository: RestaurantRepository) : RestaurantPersistencePort {

    override fun cnpjAlreadyRegistered(cnpj: String): Boolean =
        restaurantRepository.existsByCnpj(cnpj)

    override fun createRestaurant(userDomain: RestaurantDomain): RestaurantDomain =
        restaurantRepository.save(
            RestaurantEntity(
                name = userDomain.name,
                cnpj = userDomain.cnpj,
                imageUrl = userDomain.imageUrl
            )
        ).toRestaurantDomain()

    private fun RestaurantEntity.toRestaurantDomain(): RestaurantDomain =
        RestaurantDomain(
            name = name,
            cnpj = cnpj,
            imageUrl = imageUrl,
            userDomain = UserDomain(
                id = userEntity!!.id,
                email = userEntity.email
            )
        )
}
