package com.icat.orboarding

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class OrboardingApplication

fun main(args: Array<String>) {
	runApplication<OrboardingApplication>(*args)
	print(BCryptPasswordEncoder().encode("123"))
}
