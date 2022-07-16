package com.icat.orboarding.user.adapters.inbound.dtos.request

import com.icat.orboarding.user.application.domain.DeliveryDomain
import com.icat.orboarding.user.application.domain.UserDomain

data class DeliveryRequestDTO(
    val name: String,
    val cpf: String,
    val imageBase64: String,
    val user: UserRequestDTO
)

fun DeliveryRequestDTO.toDeliveryDomain(): DeliveryDomain =
    DeliveryDomain(
        fullName = name,
        cpf = cpf,
        imageUrl = imageBase64,
        userDomain = UserDomain(
            email = user.email,
            password = user.password
        )
    )