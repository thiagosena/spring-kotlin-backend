package com.thiagosena.springkotlinbackend.payload.response

data class JwtResponse(
    val token: String,
    val type: String = "Bearer",
    val person: PersonResponse
)
