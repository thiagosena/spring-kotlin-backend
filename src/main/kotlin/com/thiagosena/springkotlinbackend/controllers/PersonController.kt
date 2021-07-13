package com.thiagosena.springkotlinbackend.controllers

import com.thiagosena.springkotlinbackend.services.PersonService
import com.thiagosena.springkotlinbackend.wrappers.request.AddPersonRequest
import com.thiagosena.springkotlinbackend.wrappers.request.UpdatePersonRequest
import com.thiagosena.springkotlinbackend.wrappers.response.PersonResponse
import com.thiagosena.springkotlinbackend.wrappers.response.error.ErrorResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import javax.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Persons", description = "Persons API")
class PersonController(private val service: PersonService) {

    @GetMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "Get person object by id", description = "Get person by id", tags = ["person"])
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successful Operation, Found Person",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = PersonResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "401",
                description = "You are not authorized access the resource",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Person not found",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            )
        ]
    )
    fun getById(
        @Parameter(
            description = "Id of the contact to be obtained. Cannot be empty.",
            required = true
        )
        @PathVariable("id") id: Long
    ): PersonResponse? = service.findById(id)

    @GetMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        summary = "Get all persons with pageable",
        description = "Get all persons with pageable",
        tags = ["persons"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successful Operation",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = PersonResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "401",
                description = "You are not authorized access the resource",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            ),
        ]
    )
    fun findAll(pageable: Pageable): ResponseEntity<Page<PersonResponse>> =
        ResponseEntity.ok(this.service.findAll(pageable))

    @GetMapping("search", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        summary = "Find persons by first or last name with pageable",
        description = "Get persons filtered by first or last name with pageable",
        tags = ["persons"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successful Operation",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = PersonResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "401",
                description = "You are not authorized access the resource",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            ),
        ]
    )
    fun search(
        @Parameter(
            description = "Filter to be obtained. Cannot be empty.",
            required = true
        )
        @RequestParam filter: String,
        pageable: Pageable
    ): ResponseEntity<Page<PersonResponse>> = ResponseEntity.ok(this.service.search(filter, pageable))

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        summary = "Register a person",
        description = "Register a person",
        tags = ["person"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Person created",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = PersonResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid input",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "401",
                description = "You are not authorized access the resource",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "409",
                description = "Person already exists",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            ),
        ]
    )
    fun register(
        @Parameter(
            description = "Person to add. Cannot null or empty.",
            required = true, schema = Schema(implementation = AddPersonRequest::class)
        )
        @Valid @RequestBody addPersonRequest: AddPersonRequest
    ) = ResponseEntity(this.service.save(addPersonRequest), CREATED)

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        summary = "Update person",
        description = "Update person",
        tags = ["person"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Updated person",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = PersonResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid ID supplied",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "401",
                description = "You are not authorized access the resource",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Person not found",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "405",
                description = "Validation exception",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            )
        ]
    )
    fun update(
        @Parameter(
            description = "Person to update. Cannot null or empty.",
            required = true, schema = Schema(implementation = UpdatePersonRequest::class)
        )
        @Valid @RequestBody updatePersonRequest: UpdatePersonRequest
    ): ResponseEntity<PersonResponse> {
        return ResponseEntity.ok(this.service.update(updatePersonRequest))
    }
}
