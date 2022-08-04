package com.icat.orboarding.user.adapters.inbound.dtos.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserRequestDTO(

    @field:NotBlank
    val email: String,

    @field:Size(min = 2, max = 8)
    val password: String
)
