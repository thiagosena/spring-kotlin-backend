package com.thiagosena.springkotlinbackend.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.thiagosena.springkotlinbackend.AUTHORIZATION
import com.thiagosena.springkotlinbackend.BEARER
import com.thiagosena.springkotlinbackend.models.Credentials
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTAuthenticationFilter : UsernamePasswordAuthenticationFilter {

    private var jwtUtil: JWTUtil

    constructor(authenticationManager: AuthenticationManager, jwtUtil: JWTUtil) : super() {
        this.authenticationManager = authenticationManager
        this.jwtUtil = jwtUtil
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication? {
        val (email, password) = ObjectMapper().readValue(request.inputStream, Credentials::class.java)

        val token = UsernamePasswordAuthenticationToken(email, password)

        return authenticationManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val username = (authResult.principal as UserDetailsImpl).username
        val token = jwtUtil.generateToken(username)
        response.addHeader(AUTHORIZATION, "$BEARER $token")
    }
}
