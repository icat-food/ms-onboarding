package com.icat.orboarding.user.application.services

import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.exceptions.CnpjAlreadyRegisteredException
import com.icat.orboarding.user.application.ports.inbound.UserServicePort
import com.icat.orboarding.user.application.ports.outbound.RestaurantPersistencePort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

internal class RestaurantServiceTest {

    @Mock
    private lateinit var restaurantPersistencePort: RestaurantPersistencePort

    @Mock
    private lateinit var userServicePort: UserServicePort

    @InjectMocks
    private lateinit var restaurantService: RestaurantService

    private lateinit var userDomainMock: UserDomain

    private lateinit var restaurantDomainMock: RestaurantDomain

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        userDomainMock = UserDomain(
            id = "8313b18b-95a6-4594-9d1c-84600de83025",
            email = "kaike@gmail.com",
            password = "123",
            createdAt = LocalDateTime.parse("2022-07-02T01:52:30"),
            updatedAt = LocalDateTime.parse("2022-07-03T01:52:30")
        )

        restaurantDomainMock = RestaurantDomain(
            id = "ca28f8bf-ae2e-403e-bcfd-ab7a97d071c6",
            name = "Royal Canil Store",
            cnpj = "60694588000152",
            imageUrl = "http://s3.amazonaws.com/restaurants/880ff38e-fa34-11ec-b939-0242ac120002",
            userDomain = userDomainMock,
            createdAt = LocalDateTime.parse("2022-07-02T01:53:30"),
            updatedAt = LocalDateTime.parse("2022-07-03T01:53:30")
        )
    }

    @Test
    fun createRestaurant() {
        val requestRestaurantDomain = restaurantDomainMock.copy(id = null)
        val expectedRestaurantDomain = restaurantDomainMock.copy(id = null)
        val requestUserDomain = userDomainMock.copy(id = null)
        val expectedUserDomain = userDomainMock.copy(password = null)

        `when`(restaurantPersistencePort.cnpjAlreadyRegistered(anyString())).thenReturn(false)
        `when`(userServicePort.createUser(requestUserDomain)).thenReturn(expectedUserDomain)
        `when`(restaurantService.createRestaurant(requestRestaurantDomain)).thenReturn(expectedRestaurantDomain)

        val actualRestaurantDomain = restaurantService.createRestaurant(requestRestaurantDomain)

        assertEquals(expectedRestaurantDomain, actualRestaurantDomain)
    }

    @Test
    fun `should throw CnpjAlreadyRegisteredException if the cnpj already registered`() {
        val requestRestaurantDomain = restaurantDomainMock.copy(id = null)

        `when`(restaurantPersistencePort.cnpjAlreadyRegistered(anyString())).thenReturn(true)

        assertThrows(CnpjAlreadyRegisteredException::class.java) {
            restaurantService.createRestaurant(requestRestaurantDomain)
        }
    }
}
