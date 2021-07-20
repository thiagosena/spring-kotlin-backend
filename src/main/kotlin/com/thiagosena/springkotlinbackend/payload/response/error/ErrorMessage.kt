package com.thiagosena.springkotlinbackend.payload.response.error

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import org.springframework.http.HttpStatus

data class ErrorMessage(
    @field:Schema(
        description = "error message",
        example = "You are not authorized access the resource",
        nullable = false
    )
    val message: String,

    @field:Schema(
        description = "status of the error",
        example = "UNAUTHORIZED",
        nullable = false
    )
    val status: HttpStatus,

    @field:Schema(
        description = "status code of the error",
        example = "401",
        nullable = false
    )
    val statusCode: Int,

    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT")
    @field:Schema(
        description = "date time of the error",
        example = "2021-07-13 00:41",
        nullable = false
    )
    val dateTime: LocalDateTime = LocalDateTime.now()
)
