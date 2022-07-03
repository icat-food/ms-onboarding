package com.icat.orboarding.user.application.services

import com.icat.orboarding.user.application.domain.UserDomain
import com.icat.orboarding.user.application.exceptions.EmailAlreadyRegisteredException
import com.icat.orboarding.user.application.exceptions.UserNotFoundException
import com.icat.orboarding.user.application.ports.outbound.UserPersistencePort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.*

internal class UserServiceTest {

    @Mock
    private lateinit var userPersistencePort: UserPersistencePort

    @InjectMocks
    private lateinit var userService: UserService

    private lateinit var userDomainMock: UserDomain

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        userDomainMock = UserDomain(id = "8313b18b-95a6-4594-9d1c-84600de83025", email = "kaike@gmail.com", password = "123")
    }

    @Test
    fun `should create a new user`() {
        val requestUserDomain = userDomainMock.copy(id = null)
        val expectedUserDomain = userDomainMock.copy(password = null)

        `when`(userPersistencePort.emailAlreadyRegistered(anyString())).thenReturn(false)
        `when`(userPersistencePort.createUser(requestUserDomain)).thenReturn(expectedUserDomain)

        val actualUserDomain = userService.createUser(requestUserDomain)

        assertEquals(expectedUserDomain, actualUserDomain)
    }

    @Test
    fun `should throw EmailAlreadyRegisteredException if the email already registered`() {
        val requestUserDomain = userDomainMock.copy(id = null)

        `when`(userPersistencePort.emailAlreadyRegistered(anyString())).thenReturn(true)

        assertThrows(EmailAlreadyRegisteredException::class.java) {
            userService.createUser(requestUserDomain)
        }
    }

    @Test
    fun `should return a user by email`() {
        `when`(userPersistencePort.getUser(anyString())).thenReturn(Optional.of(userDomainMock))

        val actualUserDomain = userService.getUser("kaike@gmail.com")

        assertEquals(userDomainMock, actualUserDomain)
    }

    @Test
    fun `should throw UserNotFoundException if the user doesn't exists`() {
        `when`(userPersistencePort.getUser(anyString())).thenReturn(Optional.empty())

        assertThrows(UserNotFoundException::class.java) {
            userService.getUser("kaike@gmail.com")
        }
    }
}
