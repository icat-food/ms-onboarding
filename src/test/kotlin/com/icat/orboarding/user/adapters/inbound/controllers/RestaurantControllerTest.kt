package com.icat.orboarding.user.adapters.inbound.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.icat.orboarding.user.adapters.inbound.dtos.request.RestaurantRequestDTO
import com.icat.orboarding.user.adapters.inbound.dtos.request.UserRequestDTO
import com.icat.orboarding.user.anyObject
import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.ports.inbound.RestaurantServicePort
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
internal class RestaurantControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userServicePort: RestaurantServicePort

    private lateinit var objectMapper: ObjectMapper

    private lateinit var userDomainMock: UserDomain

    private lateinit var restaurantDomainMock: RestaurantDomain

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        objectMapper = ObjectMapper()

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
        val request = RestaurantRequestDTO(
            name = "Royal Canil Store",
            cnpj = "60694588000152",
            imageBase64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAsEAAAIWCAIAAAA56M...",
            user = UserRequestDTO(
                email = "kaike@gmail.com",
                password = "123"
            )
        )

        Mockito.`when`(userServicePort.createRestaurant(anyObject(RestaurantDomain::class.java))).thenReturn(restaurantDomainMock)

        mockMvc.post("api/v1/restaurant") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isCreated() }
            jsonPath("$.id", `is`("ca28f8bf-ae2e-403e-bcfd-ab7a97d071c6"))
            jsonPath("$.name", `is`("Royal Canil Store"))
            jsonPath("$.cnpj", `is`("60694588000152"))
            jsonPath("$.imageUrl", `is`("http://s3.amazonaws.com/restaurants/880ff38e-fa34-11ec-b939-0242ac120002"))
            jsonPath("$.user.id", `is`("8313b18b-95a6-4594-9d1c-84600de83025"))
            jsonPath("$.user.email", `is`("kaike@gmail.com"))
        }
    }
}
