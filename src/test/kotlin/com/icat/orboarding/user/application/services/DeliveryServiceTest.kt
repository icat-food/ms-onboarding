package com.icat.orboarding.user.application.services

import com.icat.orboarding.user.anyObject
import com.icat.orboarding.user.application.domain.DeliveryDomain
import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.exceptions.CpfAlreadyRegisteredException
import com.icat.orboarding.user.application.ports.inbound.UserServicePort
import com.icat.orboarding.user.application.ports.outbound.DeliveryPersistencePort
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class DeliveryServiceTest {

    @Mock private lateinit var deliveryPersistencePort: DeliveryPersistencePort
    @Mock private lateinit var userServicePort: UserServicePort
    @InjectMocks private lateinit var deliveryService: DeliveryService
    private lateinit var userDomainMock: UserDomain
    private lateinit var deliveryDomainMock: DeliveryDomain

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        userDomainMock = UserDomain(
            id = "8313b18b-95a6-4594-9d1c-84600de83025",
            email = "lucy.dona_redonda@auau.com.br",
            password = "123",
            createdAt = LocalDateTime.parse("2022-07-21T22:16:37"),
            updatedAt = LocalDateTime.parse("2022-07-21T22:16:37")
        )

        deliveryDomainMock = DeliveryDomain(
            id = "370c4fe2-9afc-4fb6-b0dc-cebadeb48111",
            name = "Agostinho Carrara",
            cpf = "94576812489",
            imageUrl = "http://s3.amazonaws.com/delivery/370c4fe2-9afc-4fb6-b0dc-cebadeb48111",
            userDomain = userDomainMock,
            createdAt = LocalDateTime.parse("2022-07-21T22:16:37"),
            updatedAt = LocalDateTime.parse("2022-07-21T22:16:37")
        )
    }

    @Test
    fun `should create a new delivery user`() {
        val requestDeliveryDomain = deliveryDomainMock.copy(id = null)
        val expectedDeliveryDomain = deliveryDomainMock.copy(id = null)
        val requestUserDomain = userDomainMock.copy(id = null)
        val expectedUserDomain = userDomainMock.copy(password = null)

        Mockito.`when`(deliveryPersistencePort.isAlreadyRegistered(Mockito.anyString())).thenReturn(false)
        Mockito.`when`(userServicePort.createUser(requestUserDomain)).thenReturn(expectedUserDomain)
        Mockito.`when`(deliveryPersistencePort.createDelivery(anyObject(DeliveryDomain::class.java))).thenReturn(expectedDeliveryDomain)

        val actualDeliveryDomain = deliveryService.createDelivery(requestDeliveryDomain)

        Assertions.assertEquals(expectedDeliveryDomain, actualDeliveryDomain)
    }

    @Test
    fun `should throw CpfAlreadyRegisteredException if cpf already registered`() {
        val requestRestaurantDomain = deliveryDomainMock.copy(id = null)

        Mockito.`when`(deliveryPersistencePort.isAlreadyRegistered(Mockito.anyString())).thenReturn(true)

        Assertions.assertThrows(CpfAlreadyRegisteredException::class.java) {
            deliveryService.createDelivery(requestRestaurantDomain)
        }
    }

}