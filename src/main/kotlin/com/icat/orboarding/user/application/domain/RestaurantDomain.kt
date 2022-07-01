package com.icat.orboarding.user.application.domain

data class RestaurantDomain(
    val name: String,
    val cnpj: String,
    val imageUrl: String,
    val userDomain: UserDomain? = null
)
