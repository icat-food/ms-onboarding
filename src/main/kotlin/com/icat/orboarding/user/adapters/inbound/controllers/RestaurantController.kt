package com.icat.orboarding.user.adapters.inbound.controllers

import com.icat.orboarding.user.adapters.inbound.dtos.request.RestaurantRequestDTO
import com.icat.orboarding.user.adapters.inbound.dtos.request.toRestaurantDomain
import com.icat.orboarding.user.adapters.inbound.dtos.response.RestaurantResponseDTO
import com.icat.orboarding.user.adapters.inbound.dtos.response.toRestaurantResponseDTO
import com.icat.orboarding.user.application.ports.inbound.RestaurantServicePort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/restaurant")
class RestaurantController(private val restaurantServicePort: RestaurantServicePort) {

    @PostMapping
    fun createRestaurant(@RequestBody restaurantRequestDTO: RestaurantRequestDTO): ResponseEntity<RestaurantResponseDTO> {
        val createdRestaurant = restaurantServicePort.createRestaurant(restaurantRequestDTO.toRestaurantDomain())

        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant.toRestaurantResponseDTO())
    }
}
