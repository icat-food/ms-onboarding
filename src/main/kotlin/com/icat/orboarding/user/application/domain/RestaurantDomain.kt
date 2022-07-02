package com.icat.orboarding.user.application.domain

import java.time.LocalDateTime

data class RestaurantDomain(
    val id: String? = null,
    val name: String,
    val cnpj: String,
    val imageUrl: String,
    val userDomain: UserDomain? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)
