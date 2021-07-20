package com.thiagosena.springkotlinbackend.payload.request

import io.swagger.v3.oas.annotations.media.Schema

data class AddPersonRequest(
    @field:Schema(
        description = "email of person",
        pattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$",
        example = "admin@abc.com",
        nullable = false
    )
    val email: String,

    @field:Schema(
        description = "first name of person",
        minLength = 2,
        maxLength = 128,
        example = "Irene",
        nullable = false
    )
    val firstName: String,

    @field:Schema(
        description = "last name of person",
        minLength = 2,
        maxLength = 128,
        example = "Santos",
        nullable = false
    )
    val lastName: String,

    @field:Schema(
        description = "password of person",
        example = "123!@#",
        nullable = false
    )
    val password: String
)
