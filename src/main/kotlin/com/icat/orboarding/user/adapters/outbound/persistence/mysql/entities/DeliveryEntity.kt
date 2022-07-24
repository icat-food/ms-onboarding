package com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities

import com.icat.orboarding.user.application.domain.DeliveryDomain
import com.icat.orboarding.user.application.domain.UserDomain
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "delivery")
class DeliveryEntity(
    @Id
    @Column(unique = true, nullable = false, length = 50)
    val id: String = UUID.randomUUID().toString(),

    @Column(name = "full_name", nullable = false, length = 100)
    val fullName: String,

    @Column(unique = true, nullable = false, length = 11)
    val cpf: String,

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

fun DeliveryEntity.toDeliveryDomain(): DeliveryDomain =
    DeliveryDomain(
        id = id,
        name = fullName,
        cpf = cpf,
        imageUrl = imageUrl,
        userDomain = UserDomain(
            id = userEntity!!.id,
            email = userEntity.email,
            password = userEntity.password,
            createdAt = userEntity.createdAt
        ),
        createdAt = createdAt,
        updatedAt = updatedAt
    )

fun DeliveryDomain.toDeliveryEntity(): DeliveryEntity =
    DeliveryEntity(
        fullName = name,
        cpf = cpf,
        imageUrl = imageUrl!!,
        userEntity = UserEntity(
            id = userDomain!!.id!!,
            email = userDomain.email,
            password = userDomain.password!!,
            createdAt = userDomain.createdAt
        ),
        createdAt = createdAt,
        updatedAt = updatedAt
    )
