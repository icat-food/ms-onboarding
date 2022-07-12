package com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.*
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
    private val password: String,

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "roles_id", referencedColumnName = "id")])
    val roles: Set<RoleEntity>? = null
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return roles
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
