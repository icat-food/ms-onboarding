package com.icat.orboarding.user.adapters.inbound.dtos.request

import com.icat.orboarding.user.application.domain.DeliveryDomain
import com.icat.orboarding.user.application.domain.UserDomain
import org.hibernate.validator.constraints.br.CPF
import javax.validation.Valid
import javax.validation.constraints.NotBlank

data class DeliveryRequestDTO(

    @field:NotBlank
    val name: String,

    @field:CPF
    val cpf: String,

    @field:NotBlank
    val imageBase64: String,

    @field:Valid
    val user: UserRequestDTO
)

fun DeliveryRequestDTO.toDeliveryDomain(): DeliveryDomain =
    DeliveryDomain(
        name = name,
        cpf = cpf,
        imageUrl = imageBase64,
        userDomain = UserDomain(
            email = user.email,
            password = user.password
        )
    )