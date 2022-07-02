package com.icat.orboarding.user.adapters.outbound.persistence.entities

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "role")
class Role : GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Int? = null

    @Column(nullable = false, unique = true)
    private val name: String? = null

    override fun getAuthority(): String? {
        return name
    }
}