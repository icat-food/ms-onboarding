package com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "user")
class UserEntity(
    @Id
    @Column(unique = true, nullable = false, length = 50)
    val id: String = UUID.randomUUID().toString(),

    @Column(unique = true, nullable = false, length = 100)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @OneToOne(mappedBy = "userEntity")
    private val consumerEntity: ConsumerEntity? = null,

    @OneToOne(mappedBy = "userEntity")
    private val restaurantEntity: RestaurantEntity? = null,

    @OneToOne(mappedBy = "userEntity")
    val deliveryEntity: DeliveryEntity? = null,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null
)
