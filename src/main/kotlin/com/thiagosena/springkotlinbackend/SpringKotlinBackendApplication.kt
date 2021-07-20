package com.thiagosena.springkotlinbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SpringKotlinBackendApplication

fun main() {
    runApplication<SpringKotlinBackendApplication>()
}
