package com.icat.orboarding.user.adapters.outbound.persistence.mysql

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.UserEntity
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories.UserRepository
import com.icat.orboarding.user.application.domain.UserDomain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.stereotype.Component

@DataJpaTest(includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [(Component::class)])])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class UserPersistenceTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userPersistence: UserPersistence

    @BeforeEach
    fun setUp() {
        userRepository.save(UserEntity(email = "kaike@gmail.com", password = "123"))
    }

    @Test
    fun `should return true if the email already exists`() {
        assertTrue(userPersistence.emailAlreadyRegistered("kaike@gmail.com"))
    }

    @Test
    fun `should return false if the email already exists`() {
        assertFalse(userPersistence.emailAlreadyRegistered("van@gmail.com"))
    }

    @Test
    fun `should create a user in database`() {
        val email = "donnie@gmail.com"
        userPersistence.createUser(UserDomain(email = email, password = "123"))
        val persistedUserEmail = userRepository.findByEmailIgnoreCase(email).get()

        assertEquals(email, persistedUserEmail.email)
    }

    @Test
    fun `should return a user in database by email`() {
        val user = userPersistence.getUser("kaike@gmail.com")

        assertTrue(user.isPresent)
    }

    @Test
    fun `should return a empty optional if email doesn't exists`() {
        val user = userPersistence.getUser("donnie@gmail.com")

        assertTrue(user.isEmpty)
    }

    @Test
    fun `should update a user by current email`() {
        val currentEmail = "kaike@gmail.com"
        val newEmail = "kaike@gmail.com"
        val currentUser = userPersistence.getUser(currentEmail).get()
        val updatedUser = userPersistence.updateUser(UserDomain(id = currentUser.id, email = newEmail, password = "321"))

        assertEquals(currentUser.id, updatedUser.id)
        assertEquals(currentEmail, currentUser.email)
        assertEquals(newEmail, updatedUser.email)
        assertEquals("321", updatedUser.password)
    }
}
