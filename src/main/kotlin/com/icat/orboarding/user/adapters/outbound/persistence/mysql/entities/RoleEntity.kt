package com.icat.orboarding.user.adapters.outbound.persistence.mysql.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "role")
class RoleEntity(

    @Id
    @Column(unique = true, nullable = false, length = 50)
    val id: String? = UUID.randomUUID().toString(),

    @Column(nullable = false, unique = true)
    private val name: String? = null,

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    @JsonIgnoreProperties("roles")
    val roles: Set<UserEntity>? = null

) : GrantedAuthority {

    override fun getAuthority(): String? {
        return name
    }
}