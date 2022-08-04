package com.icat.orboarding.user.adapters.inbound.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.icat.orboarding.user.adapters.inbound.dtos.request.LoginDTO
import com.icat.orboarding.user.adapters.inbound.dtos.request.RestaurantRequestDTO
import com.icat.orboarding.user.adapters.inbound.dtos.request.UserRequestDTO
import com.icat.orboarding.user.shared.factory.RequestDTOFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureMockMvc
internal class AuthenticationControllerTest {
    private lateinit var mockMvc: MockMvc
    private lateinit var loginDTO: LoginDTO
    private lateinit var objectMapper: ObjectMapper
    private lateinit var restaurantRequestDTO: RestaurantRequestDTO
    @Autowired private lateinit var context: WebApplicationContext

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(springSecurity())
            .build()

        MockitoAnnotations.openMocks(this)
        objectMapper = ObjectMapper()
        loginDTO = LoginDTO(
            email = "choraste_beicola@pasteis.com",
            password = "123"
        )

        val userRequestDTO = UserRequestDTO(email = "kaike@gmail.com", password = "123")
        restaurantRequestDTO = RequestDTOFactory.anyRestaurantDTO(userRequestDTO)
    }

    @Test
    @WithUserDetails("eilson.risca_faca@xuragou.com")
    fun loginWithAnyValidUSerShouldBeSuccessful() {
        mockMvc.post("/api/v1/auth") {
            content = objectMapper.writeValueAsString(loginDTO)
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
    }

    @Test
    @WithUserDetails("kaikeira.store@xurastei.com")
    fun accessRestaurantEndpointWithConsumerUserShouldReturn403Forbidden() {
        mockMvc.post("/api/v1/restaurant") {
            content = objectMapper.writeValueAsString(restaurantRequestDTO)
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isForbidden() }
        }
    }

    @Test
    @WithUserDetails("choraste_beicola@pasteis.com")
    fun accessRestaurantEndpointWithRestaurantUserShouldReturn201Created() {
        mockMvc.post("/api/v1/restaurant") {
            content = objectMapper.writeValueAsString(restaurantRequestDTO)
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isCreated() }
        }
    }

}