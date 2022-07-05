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
import java.time.LocalDateTime
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

        userDomainMock = UserDomain(
            id = "8313b18b-95a6-4594-9d1c-84600de83025",
            email = "kaike@gmail.com",
            password = "123",
            createdAt = LocalDateTime.parse("2022-07-02T01:52:30"),
            updatedAt = LocalDateTime.parse("2022-07-03T01:52:30")
        )
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

    @Test
    fun `should update a user by current email`() {
        val expectedUserDomain = userDomainMock.copy(email = "van@gmail.com", password = "321")

        `when`(userPersistencePort.emailAlreadyRegistered(anyString())).thenReturn(false)
        `when`(userPersistencePort.getUser(anyString())).thenReturn(Optional.of(userDomainMock))
        `when`(userPersistencePort.updateUser(userDomainMock)).thenReturn(expectedUserDomain)

        val actualUserDomain = userService.updateUser("kaike@gmail.com", userDomainMock)

        assertEquals("van@gmail.com", actualUserDomain.email)
        assertEquals("321", actualUserDomain.password)
    }

    @Test
    fun `should throw EmailAlreadyRegisteredException when update user if the new email already registered`() {
        `when`(userPersistencePort.emailAlreadyRegistered(anyString())).thenReturn(true)

        assertThrows(EmailAlreadyRegisteredException::class.java) {
            userService.updateUser("kaike@gmail.com", userDomainMock)
        }
    }

    @Test
    fun `should throw UserNotFoundException when update user and user not found by current email`() {
        `when`(userPersistencePort.getUser(anyString())).thenReturn(Optional.empty())

        assertThrows(UserNotFoundException::class.java) {
            userService.updateUser("kaike@gmail.com", userDomainMock)
        }
    }
}
