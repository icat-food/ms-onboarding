package com.icat.orboarding.user.adapters.outbound.persistence.entities

import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "consumer")
class ConsumerEntity(
    @Id
    @Column(unique = true, nullable = false, length = 50)
    val id: String,

    @Column(name = "full_name", nullable = false, length = 100)
    val fullName: String,

    @Column(unique = true, nullable = false, length = 11)
    val cpf: String,

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
