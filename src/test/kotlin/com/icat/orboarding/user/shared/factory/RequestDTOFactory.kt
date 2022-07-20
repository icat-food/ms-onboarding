package com.icat.orboarding.user.shared.factory

import com.icat.orboarding.user.adapters.inbound.dtos.request.RestaurantRequestDTO
import com.icat.orboarding.user.adapters.inbound.dtos.request.UserRequestDTO

class RequestDTOFactory {
    companion object{
        fun anyRestaurantDTO(userRequestDTO: UserRequestDTO): RestaurantRequestDTO =
            RestaurantRequestDTO(
                name = "Royal Canil Store",
                cnpj = "60694588000152",
                imageBase64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAsEAAAIWCAIAAAA56M...",
                user = UserRequestDTO(
                    email = userRequestDTO.email,
                    password = userRequestDTO.password
                )
            )

    }
}