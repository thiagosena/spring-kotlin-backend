package com.thiagosena.springkotlinbackend.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.thiagosena.springkotlinbackend.security.commons.ErrorUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val objectMapper = ObjectMapper().registerModule(KotlinModule())

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        ErrorUtils.getErrorMessage(
            authException.localizedMessage,
            response,
            objectMapper,
            FORBIDDEN
        )
    }
}
