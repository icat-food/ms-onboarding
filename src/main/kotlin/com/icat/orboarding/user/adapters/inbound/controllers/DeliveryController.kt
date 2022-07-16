package com.icat.orboarding.user.adapters.inbound.controllers

import com.icat.orboarding.user.adapters.inbound.dtos.request.DeliveryRequestDTO
import com.icat.orboarding.user.adapters.inbound.dtos.request.toDeliveryDomain
import com.icat.orboarding.user.adapters.inbound.dtos.response.DeliveryResponseDTO
import com.icat.orboarding.user.application.domain.toDeliveryResponseDTO
import com.icat.orboarding.user.application.ports.inbound.DeliveryServicePort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/delivery")
class DeliveryController(private val deliveryServicePort: DeliveryServicePort) {

    @PostMapping
    fun createDelivery(@RequestBody deliveryRequestDTO: DeliveryRequestDTO): ResponseEntity<DeliveryResponseDTO> {
        val createdDelivery = deliveryServicePort.createDeliveryPerson(deliveryRequestDTO.toDeliveryDomain())
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDelivery.toDeliveryResponseDTO())
    }
}