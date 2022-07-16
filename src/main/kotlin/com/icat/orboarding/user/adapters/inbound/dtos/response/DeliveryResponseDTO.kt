package com.icat.orboarding.user.adapters.inbound.dtos.response

data class DeliveryResponseDTO(
    val id: String,
    val name: String,
    val cpf: String,
    val imageUrl: String,
    val user: UserResponseDTO
    )