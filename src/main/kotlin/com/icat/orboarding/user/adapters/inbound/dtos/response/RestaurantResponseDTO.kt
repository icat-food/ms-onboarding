package com.icat.orboarding.user.adapters.inbound.dtos.response

import com.icat.orboarding.user.application.domain.RestaurantDomain
import java.time.LocalDateTime

data class RestaurantResponseDTO(
    val id: String,
    val name: String,
    val cnpj: String,
    val imageUrl: String,
    val userResponseDTO: UserResponseDTO,
    val createdAt: LocalDateTime
)

fun RestaurantDomain.toRestaurantResponseDTO(): RestaurantResponseDTO =
    RestaurantResponseDTO(
        id = id!!,
        name = name,
        cnpj = cnpj,
        imageUrl = imageUrl!!,
        userResponseDTO = UserResponseDTO(
            id = userDomain!!.id!!,
            email = userDomain.email,
            createdAt = userDomain.createdAt!!
        ),
        createdAt = createdAt!!
    )
