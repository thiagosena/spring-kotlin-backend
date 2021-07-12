package com.thiagosena.springkotlinbackend.handlers

import javax.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(e: EntityNotFoundException): ResponseEntity<ErrorResponse> {
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

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalState(ex: IllegalStateException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(
                ErrorMessage(
                    ex.localizedMessage,
                    NOT_FOUND,
                    NOT_FOUND.value()
                )
            )
        )
    }
}
