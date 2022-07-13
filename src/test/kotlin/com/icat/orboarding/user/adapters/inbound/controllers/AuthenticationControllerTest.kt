package com.icat.orboarding.user.adapters.inbound.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.icat.orboarding.user.adapters.inbound.dtos.request.LoginDTO
import com.icat.orboarding.user.application.ports.inbound.TokenServicePort
import org.hamcrest.Matchers
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

    @Autowired
    private lateinit var tokenService: TokenServicePort

    private lateinit var loginDTO: LoginDTO
    private lateinit var objectMapper: ObjectMapper
    private lateinit var mockedGeneratedToken: String

    @Autowired
    private lateinit var context: WebApplicationContext


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
    }

    @Test
    @WithUserDetails("choraste_beicola@pasteis.com")
    fun loginShouldBeSuccessful() {
        mockMvc.post("/api/v1/auth") {
            content = objectMapper.writeValueAsString(loginDTO)
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
    }

}