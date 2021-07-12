package com.thiagosena.springkotlinbackend.handlers

import java.time.LocalDateTime
import org.springframework.http.HttpStatus

class ErrorMessage(
    val message: String,
    val status: HttpStatus,
    val statusCode: Int,
    val dateTime: LocalDateTime = LocalDateTime.now()
)
