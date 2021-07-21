package com.thiagosena.springkotlinbackend.controllers

import com.thiagosena.springkotlinbackend.payload.request.LoginRequest
import com.thiagosena.springkotlinbackend.payload.response.JwtResponse
import com.thiagosena.springkotlinbackend.security.JWTUtil
import com.thiagosena.springkotlinbackend.security.UserDetailsImpl
import javax.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JWTUtil
) {

    @PostMapping
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<JwtResponse> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtUtils.generateToken(loginRequest.username)
        val userDetails = authentication.principal as UserDetailsImpl

        val person = userDetails.getPersonResponse()

        return ResponseEntity.ok(
            JwtResponse(token = jwt, person = person)
        )
    }
}
