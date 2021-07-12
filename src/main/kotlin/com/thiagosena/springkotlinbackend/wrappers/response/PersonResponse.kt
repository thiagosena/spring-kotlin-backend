package com.thiagosena.springkotlinbackend.wrappers.response

data class PersonResponse(
    val id: Long,
    val email: String,
    val fullName: String,
)
