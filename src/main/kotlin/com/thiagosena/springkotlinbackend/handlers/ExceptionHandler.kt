package com.thiagosena.springkotlinbackend.handlers

import com.thiagosena.springkotlinbackend.payload.response.error.ErrorMessage
import com.thiagosena.springkotlinbackend.payload.response.error.ErrorResponse
import javax.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                ErrorMessage(
                    e.localizedMessage,
                    NOT_FOUND,
                    NOT_FOUND.value()
                )
            ),
            NOT_FOUND
        )
    }

    @ExceptionHandler(IllegalStateException::class, IllegalArgumentException::class)
    fun handleIllegalStateException(ex: RuntimeException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(
                ErrorMessage(
                    ex.localizedMessage,
                    BAD_REQUEST,
                    BAD_REQUEST.value()
                )
            )
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(
                ErrorMessage(
                    ex.localizedMessage,
                    INTERNAL_SERVER_ERROR,
                    INTERNAL_SERVER_ERROR.value()
                )
            )
        )
    }
}
