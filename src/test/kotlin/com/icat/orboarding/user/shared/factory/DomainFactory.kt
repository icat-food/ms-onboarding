package com.icat.orboarding.user.shared.factory

import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.domain.UserDomain
import java.time.LocalDateTime

class DomainFactory {
    companion object{
        fun anyRestaurantDomain(userDomain: UserDomain) =
            RestaurantDomain(
                id = "ca28f8bf-ae2e-403e-bcfd-ab7a97d071c6",
                name = "Royal Canil Store",
                cnpj = "60694588000152",
                imageUrl = "http://s3.amazonaws.com/restaurants/880ff38e-fa34-11ec-b939-0242ac120002",
                userDomain = userDomain,
                createdAt = LocalDateTime.parse("2022-07-02T01:53:30"),
                updatedAt = LocalDateTime.parse("2022-07-03T01:53:30")
            )

        fun anyUserDomain() =
            UserDomain(
                id = "8313b18b-95a6-4594-9d1c-84600de83025",
                email = "vandeilson@gmail.com",
                password = "123",
                createdAt = LocalDateTime.parse("2022-07-02T01:52:30"),
                updatedAt = LocalDateTime.parse("2022-07-03T01:52:30")
            )
    }
}