package com.thiagosena.springkotlinbackend.wrappers.response.error

import java.time.LocalDateTime
import org.springframework.http.HttpStatus

data class ErrorMessage(
    val message: String,
    val status: HttpStatus,
    val statusCode: Int,
    val dateTime: LocalDateTime = LocalDateTime.now()
)
