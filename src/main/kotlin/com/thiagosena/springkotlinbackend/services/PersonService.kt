package com.thiagosena.springkotlinbackend.services

import com.thiagosena.springkotlinbackend.payload.request.AddPersonRequest
import com.thiagosena.springkotlinbackend.payload.request.UpdatePersonRequest
import com.thiagosena.springkotlinbackend.payload.response.PersonResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PersonService {
    fun findById(id: Long): PersonResponse?
    fun findAll(pageable: Pageable): Page<PersonResponse>
    fun save(addPersonRequest: AddPersonRequest): PersonResponse
    fun update(updatePersonRequest: UpdatePersonRequest): PersonResponse
    fun search(filter: String, pageable: Pageable): Page<PersonResponse>
}
