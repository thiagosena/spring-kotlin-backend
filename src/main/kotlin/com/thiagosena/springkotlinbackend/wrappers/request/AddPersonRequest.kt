package com.thiagosena.springkotlinbackend.wrappers.request

data class AddPersonRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String
)
