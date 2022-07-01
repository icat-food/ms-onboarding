package com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities

import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "restaurant")
class RestaurantEntity(
    @Id
    @Column(unique = true, nullable = false, length = 50)
    val id: String,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(unique = true, nullable = false, length = 14)
    val cnpj: String,

    @Column(unique = true, name = "image_url", nullable = false)
    val imageUrl: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val userEntity: UserEntity? = null,

    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null
)
