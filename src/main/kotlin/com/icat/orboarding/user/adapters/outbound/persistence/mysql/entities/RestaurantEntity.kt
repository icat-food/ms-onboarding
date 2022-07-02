package com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities

import com.icat.orboarding.user.application.domain.RestaurantDomain
import com.icat.orboarding.user.application.domain.UserDomain
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "restaurant")
class RestaurantEntity(
    @Id
    @Column(unique = true, nullable = false, length = 50)
    val id: String = UUID.randomUUID().toString(),

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(unique = true, nullable = false, length = 14)
    val cnpj: String,

    @Column(unique = true, name = "image_url", nullable = false)
    val imageUrl: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val userEntity: UserEntity? = null,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null
)

fun RestaurantEntity.toRestaurantDomain(): RestaurantDomain =
    RestaurantDomain(
        id = id,
        name = name,
        cnpj = cnpj,
        imageUrl = imageUrl,
        userDomain = UserDomain(
            id = userEntity!!.id,
            email = userEntity.email,
            password = userEntity.password,
            createdAt = userEntity.createdAt
        ),
        createdAt = createdAt
    )

fun RestaurantDomain.toRestaurantEntity(): RestaurantEntity =
    RestaurantEntity(
        name = name,
        cnpj = cnpj,
        imageUrl = imageUrl!!,
        userEntity = UserEntity(
            id = userDomain!!.id!!,
            email = userDomain.email,
            password = userDomain.password!!,
            createdAt = userDomain.createdAt
        )
    )
