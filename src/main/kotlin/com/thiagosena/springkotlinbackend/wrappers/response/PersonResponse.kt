package com.thiagosena.springkotlinbackend.wrappers.response

import io.swagger.v3.oas.annotations.media.Schema

data class PersonResponse(
    @field:Schema(
        description = "id of person",
        example = "1",
        nullable = false
    )
    val id: Long,

    @field:Schema(
        description = "email of person",
        pattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$",
        example = "admin@abc.com",
        nullable = false
    )
    val email: String,

    @field:Schema(
        description = "full name of person",
        example = "Santos, Irene",
        nullable = false
    )
    val fullName: String,
)
