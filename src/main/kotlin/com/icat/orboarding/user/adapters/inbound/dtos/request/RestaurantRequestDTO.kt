package com.icat.orboarding.user.adapters.inbound.dtos.request

import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.domain.UserDomain
import org.hibernate.validator.constraints.br.CNPJ
import javax.validation.Valid
import javax.validation.constraints.NotBlank

data class RestaurantRequestDTO(

    @field:NotBlank
    val name: String,

    @field:CNPJ
    val cnpj: String,

    @field:NotBlank
    val imageBase64: String,

    @field:Valid
    val user: UserRequestDTO
)

fun RestaurantRequestDTO.toRestaurantDomain(): RestaurantDomain =
    RestaurantDomain(
        name = name,
        cnpj = cnpj,
        imageBase64 = imageBase64,
        userDomain = UserDomain(
            email = user.email,
            password = user.password
        )
    )
