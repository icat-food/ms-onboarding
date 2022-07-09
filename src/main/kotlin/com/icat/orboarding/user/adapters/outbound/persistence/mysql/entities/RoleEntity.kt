package com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "role")
class RoleEntity : GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Int? = null

    @Column(nullable = false, unique = true)
    private val name: String? = null

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    @JsonIgnoreProperties("roles")
    val roles: Set<UserEntity>? = null

    override fun getAuthority(): String? {
        return name
    }
}