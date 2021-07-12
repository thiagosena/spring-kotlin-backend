package com.thiagosena.springkotlinbackend.controllers

import com.thiagosena.springkotlinbackend.services.PersonService
import com.thiagosena.springkotlinbackend.wrappers.request.AddPersonRequest
import com.thiagosena.springkotlinbackend.wrappers.request.UpdatePersonRequest
import com.thiagosena.springkotlinbackend.wrappers.response.PersonResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus.CREATED
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
class PersonController(private val service: PersonService) {

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long): PersonResponse? = service.findById(id)

    @GetMapping
    fun findAll(pageable: Pageable): ResponseEntity<Page<PersonResponse>> =
        ResponseEntity.ok(this.service.findAll(pageable))

    @GetMapping("search")
    fun search(
        @RequestParam filter: String,
        pageable: Pageable
    ): ResponseEntity<Page<PersonResponse>> = ResponseEntity.ok(this.service.search(filter, pageable))

    @PostMapping
    fun register(@RequestBody addPersonRequest: AddPersonRequest) =
        ResponseEntity(this.service.save(addPersonRequest), CREATED)

    @PutMapping
    fun update(@RequestBody updatePersonRequest: UpdatePersonRequest): ResponseEntity<PersonResponse> {
        return ResponseEntity.ok(this.service.update(updatePersonRequest))
    }
}
