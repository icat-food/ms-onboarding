package com.icat.orboarding.user.adapters.inbound.dtos.request

import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.domain.UserDomain

data class RestaurantRequestDTO(
    val name: String,
    val cnpj: String,
    val imageBase64: String,
    val user: UserRequestDTO
)

fun RestaurantRequestDTO.toRestaurantDomain(): RestaurantDomain =
    RestaurantDomain(
        name = name,
        cnpj = cnpj,
        imageBase64 = imageBase64,
        userDomain = UserDomain(
            email = user.email,
            password = user.password
        )
    )
