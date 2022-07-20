package com.icat.orboarding.user.application.domain

import java.time.LocalDateTime

data class UserDomain(
    val id: String? = null,
    val email: String,
    var password: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)
