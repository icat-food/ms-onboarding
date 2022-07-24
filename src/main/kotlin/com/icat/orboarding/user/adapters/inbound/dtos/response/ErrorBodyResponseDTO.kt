package com.icat.orboarding.user.adapters.inbound.dtos.response

data class ErrorBodyResponseDTO(
    val status: Int,
    val type: String,
    var detail: String
)
