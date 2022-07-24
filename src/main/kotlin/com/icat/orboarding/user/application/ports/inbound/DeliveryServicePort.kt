package com.icat.orboarding.user.application.ports.inbound

import com.icat.orboarding.user.application.domain.DeliveryDomain

interface DeliveryServicePort {
    fun createDelivery(deliveryDomain: DeliveryDomain): DeliveryDomain
    fun getDelivery(deliveryId: String): DeliveryDomain
}