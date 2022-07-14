package com.icat.orboarding.user.adapters.configuration

import com.icat.orboarding.user.application.domain.RolesEnum
import com.icat.orboarding.user.application.ports.inbound.AuthenticationServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    @Autowired val authenticationService: AuthenticationServicePort,
    @Autowired val tokenAuthFilterConfiguration: TokenAuthFilterConfiguration,
    ) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .parentAuthenticationManager(authenticationManagerBean())
            .userDetailsService(authenticationService).passwordEncoder(BCryptPasswordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/restaurant/**").hasRole(RolesEnum.RESTAURANT.name)
            .antMatchers(HttpMethod.POST, "/api/v1/consumer/**").hasRole(RolesEnum.CONSUMER.name)
            .antMatchers(HttpMethod.POST, "/api/v1/delivery/**").hasRole(RolesEnum.DELIVERY.name)
            .anyRequest().authenticated()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore( tokenAuthFilterConfiguration, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        super.configure(web)
    }

    @Bean
    @Throws(java.lang.Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }
}