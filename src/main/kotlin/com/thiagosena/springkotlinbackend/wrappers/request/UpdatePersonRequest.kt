package com.thiagosena.springkotlinbackend.wrappers.request

data class UpdatePersonRequest(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
