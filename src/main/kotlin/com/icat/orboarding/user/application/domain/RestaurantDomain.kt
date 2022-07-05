package com.icat.orboarding.user.application.domain

import java.time.LocalDateTime

data class RestaurantDomain(
    val id: String? = null,
    val name: String,
    val cnpj: String,
    val imageBase64: String? = null,
    val imageUrl: String? = null,
    val userDomain: UserDomain?,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)
