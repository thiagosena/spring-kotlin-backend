package com.thiagosena.springkotlinbackend.security.commons

import com.fasterxml.jackson.databind.ObjectMapper
import com.thiagosena.springkotlinbackend.payload.response.error.ErrorMessage
import com.thiagosena.springkotlinbackend.payload.response.error.ErrorResponse
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

object ErrorUtils {
    fun getErrorMessage(
        message: String,
        response: HttpServletResponse,
        objectMapper: ObjectMapper,
        status: HttpStatus
    ) {
        val responseEntity = ErrorResponse(
            ErrorMessage(
                message,
                status,
                status.value()
            )
        )

        objectMapper.findAndRegisterModules()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = status.value()
        response.writer.write(objectMapper.writeValueAsString(responseEntity))
    }
}
