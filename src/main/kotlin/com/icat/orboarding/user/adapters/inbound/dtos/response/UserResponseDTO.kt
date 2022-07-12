package com.icat.orboarding.user.adapters.inbound.dtos.response

import java.time.LocalDateTime

data class UserResponseDTO(
    val id: String,
    val email: String,
    val createdAt: LocalDateTime
)
