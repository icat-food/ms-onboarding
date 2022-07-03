package com.icat.orboarding.user.application.ports.inbound

import com.icat.orboarding.user.application.domain.UserDomain

interface UserServicePort {
    fun createUser(userDomain: UserDomain): UserDomain
<<<<<<< HEAD
    fun getUser(email: String): UserDomain
=======
>>>>>>> e423a1150d7abb52c1d307e1ab2432495e4aa20e
}
