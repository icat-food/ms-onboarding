package com.icat.orboarding.user.application.domain

data class UserDomain(
    val id: String? = null,
    val email: String,
    val password: String? = null,
)
