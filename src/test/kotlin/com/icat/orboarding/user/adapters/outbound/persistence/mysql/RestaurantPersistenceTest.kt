package com.icat.orboarding.user.adapters.outbound.persistence.mysql

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.RestaurantEntity
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities.UserEntity
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories.RestaurantRepository
import com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories.UserRepository
import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.domain.UserDomain
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component

@DataJpaTest(includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [(Component::class)])])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class RestaurantPersistenceTest {

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    @Autowired
    private lateinit var restaurantPersistence: RestaurantPersistence

    @Autowired
    private lateinit var userRepository: UserRepository

    private lateinit var userEntity: UserEntity

    @BeforeEach
    fun setUp() {
        userEntity = userRepository.save(UserEntity(email = "petrucio@gmail.com", password = "123"))
        restaurantRepository.save(
            RestaurantEntity(
                name = "Gran Plus Store",
                cnpj = "80766675000185",
                imageUrl = "http://s3.amazonaws.com/restaurants/706c1d5b-2afc-42d1-9c59-ef3fc5aa4295",
                userEntity = userEntity
            )
        )
    }

    @Test
    fun `should return true if the cnpj already exists`() {
        assertTrue(restaurantPersistence.cnpjAlreadyRegistered("80766675000185"))
    }

    @Test
    fun `should return false if the cnpj already exists`() {
        assertFalse(restaurantPersistence.cnpjAlreadyRegistered("92882956000182"))
    }

    @Test
    fun `should create a restaurant in database`() {
        val cnpj = "61187113000132"
        userEntity = userRepository.save(UserEntity(email = "jepeto@gmail.com", password = "123"))
        val restaurantDomain = RestaurantDomain(
            name = "Royal Canin Store",
            cnpj = cnpj,
            imageUrl = "http://s3.amazonaws.com/restaurants/880ff38e-fa34-11ec-b939-0242ac120002",
            userDomain = UserDomain(
                userEntity.id,
                userEntity.email,
                userEntity.password
            )
        )
        restaurantPersistence.createRestaurant(restaurantDomain)
        val persistedRestaurant = restaurantRepository.findByCnpj(cnpj).get()

        assertEquals(cnpj, persistedRestaurant.cnpj)
    }

    @Test
    fun `should throw DataIntegrityViolationException if the cnpj already exists`() {
        val cnpj = "80766675000185"
        val restaurantDomain = RestaurantDomain(
            name = "Royal Canin Store",
            cnpj = cnpj,
            imageUrl = "http://s3.amazonaws.com/restaurants/880ff38e-fa34-11ec-b939-0242ac120002",
            userDomain = UserDomain(
                userEntity.id,
                userEntity.email,
                userEntity.password
            )
        )

        assertThrows(DataIntegrityViolationException::class.java) {
            restaurantPersistence.createRestaurant(restaurantDomain)
            restaurantRepository.findByCnpj(cnpj).get()
        }
    }

    @Test
    fun `should throw DataIntegrityViolationException if the imageUrl already exists`() {
        val cnpj = "80766675000185"
        userRepository.save(UserEntity(email = "petrucios@gmail.com", password = "123"))
        val restaurantDomain = RestaurantDomain(
            name = "Royal Canin Store",
            cnpj = cnpj,
            imageUrl = "http://s3.amazonaws.com/restaurants/706c1d5b-2afc-42d1-9c59-ef3fc5aa4295",
            userDomain = UserDomain(
                userEntity.id,
                userEntity.email,
                userEntity.password
            )
        )

        assertThrows(DataIntegrityViolationException::class.java) {
            restaurantPersistence.createRestaurant(restaurantDomain)
            restaurantRepository.findByCnpj(cnpj).get()
        }
    }

    @Test
    fun `should throw DataIntegrityViolationException if the user has more than one restaurant`() {
        val cnpj = "61187113000132"
        val restaurantDomain = RestaurantDomain(
            name = "Royal Canin Store",
            cnpj = cnpj,
            imageUrl = "http://s3.amazonaws.com/restaurants/880ff38e-fa34-11ec-b939-0242ac120002",
            userDomain = UserDomain(
                userEntity.id,
                userEntity.email,
                userEntity.password
            )
        )
        assertThrows(DataIntegrityViolationException::class.java) {
            restaurantPersistence.createRestaurant(restaurantDomain)
            restaurantRepository.findByCnpj(cnpj).get()
        }
    }
}
