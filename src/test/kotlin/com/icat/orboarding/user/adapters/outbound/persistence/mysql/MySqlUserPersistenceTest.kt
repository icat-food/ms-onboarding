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
internal class MySqlUserPersistenceTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var mySqlUserPersistence: MySqlUserPersistence

    @BeforeEach
    fun setUp() {
        userRepository.save(UserEntity(email = "kaike@gmail.com", password = "123"))
    }

    @Test
    fun `should return true if the email already exists`() {
        assertTrue(mySqlUserPersistence.emailAlreadyRegistered("kaike@gmail.com"))
    }

    @Test
    fun `should return false if the email already exists`() {
        assertFalse(mySqlUserPersistence.emailAlreadyRegistered("van@gmail.com"))
    }

    @Test
    fun `should create a user in database`() {
        mySqlUserPersistence.createUser(UserDomain(email = "donnie@gmail.com", password = "123"))
        val persistedUserEmail = userRepository.findAll()[1].email

        assertEquals(2, userRepository.count())
        assertEquals("donnie@gmail.com", persistedUserEmail)
    }
}
