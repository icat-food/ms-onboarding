package com.icat.orboarding.user.adapters.configuration

import com.icat.orboarding.user.adapters.outbound.persistence.mysql.repositories.UserRepository
import com.icat.orboarding.user.application.ports.inbound.TokenServicePort
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenAuthFilterConfiguration(
    private val tokenService: TokenServicePort,
    private val repository: UserRepository
    ) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val tokenFromHeader = getTokenFromHeader(request)
        val tokenValid = tokenService.isTokenValid(tokenFromHeader)
        if (tokenValid) {
            this.authenticate(tokenFromHeader!!)
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenFromHeader(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        return if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            null
        } else token.substring(7, token.length)
    }

    private fun authenticate(tokenFromHeader: String) {
        val email = tokenService.getTokenId(tokenFromHeader)
        val optionalUser = repository.findByEmailIgnoreCase(email!!)
        if (optionalUser.isPresent) {
            val user = optionalUser.get()
            val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(user, null, user.authorities)
            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
        }
    }
}