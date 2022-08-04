package com.icat.orboarding.user.adapters.inbound.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.icat.orboarding.user.adapters.inbound.dtos.request.DeliveryRequestDTO
import com.icat.orboarding.user.adapters.inbound.dtos.request.UserRequestDTO
import com.icat.orboarding.user.anyObject
import com.icat.orboarding.user.application.domain.DeliveryDomain
import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.ports.inbound.DeliveryServicePort
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var deliveryServicePort: DeliveryServicePort

    private lateinit var objectMapper: ObjectMapper

    private lateinit var userDomainMock: UserDomain

    private lateinit var deliveryDomainMock: DeliveryDomain

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        objectMapper = ObjectMapper()

        userDomainMock = UserDomain(
            id = "8313b18b-95a6-4594-9d1c-84600de83021",
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
    @WithUserDetails("eilson.risca_faca@xuragou.com")
    fun `create Delivery should return 201 created`() {
        val deliveryRequestDTO = DeliveryRequestDTO(
            name = "Agostinho Carrara",
            cpf = "94576812489",
            imageBase64 = "http://s3.amazonaws.com/delivery/370c4fe2-9afc-4fb6-b0dc-cebadeb48111",
            user = UserRequestDTO(
                email = "lucy.dona_redonda@auau.com.br",
                password = "123"
            )
        )

        Mockito.`when`(deliveryServicePort.createDelivery(anyObject(DeliveryDomain::class.java))).thenReturn(deliveryDomainMock)

        mockMvc.post("/api/v1/delivery") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(deliveryRequestDTO)
        }.andExpect {
            status { isCreated() }
            jsonPath("$.id", Matchers.`is`("370c4fe2-9afc-4fb6-b0dc-cebadeb48111"))
            jsonPath("$.name", Matchers.`is`("Agostinho Carrara"))
            jsonPath("$.cpf", Matchers.`is`("94576812489"))
            jsonPath("$.imageUrl",
                Matchers.`is`("http://s3.amazonaws.com/delivery/370c4fe2-9afc-4fb6-b0dc-cebadeb48111")
            )
            jsonPath("$.user.id", Matchers.`is`("8313b18b-95a6-4594-9d1c-84600de83021"))
            jsonPath("$.user.email", Matchers.`is`("lucy.dona_redonda@auau.com.br"))
        }
    }

    @Test
    fun `when get Delivery should return 200 ok`() {

        Mockito.`when`(deliveryServicePort.getDelivery(anyString())).thenReturn(deliveryDomainMock)

        mockMvc.get("/api/v1/delivery/123").andExpect {
            status { isOk() }
        }

    }
}