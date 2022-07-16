package com.icat.orboarding.user.application.domain

import com.icat.orboarding.user.adapters.inbound.dtos.response.DeliveryResponseDTO
import com.icat.orboarding.user.adapters.inbound.dtos.response.UserResponseDTO
import java.time.LocalDateTime


data class DeliveryDomain (
    val id: String? = null,
    val fullName: String,
    val cpf: String,
    val imageUrl: String?,
    val userDomain: UserDomain?,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
        )

fun DeliveryDomain.toDeliveryResponseDTO(): DeliveryResponseDTO =
    DeliveryResponseDTO(
        id = id!!,
        name = fullName,
        cpf = cpf,
        imageUrl = imageUrl!!,
        user = UserResponseDTO(
            id = userDomain!!.id!!,
            email = userDomain.email,
            createdAt = userDomain.createdAt!!
        )
    )