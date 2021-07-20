package com.thiagosena.springkotlinbackend.services.impl

import com.thiagosena.springkotlinbackend.models.Person
import com.thiagosena.springkotlinbackend.payload.request.AddPersonRequest
import com.thiagosena.springkotlinbackend.payload.request.UpdatePersonRequest
import com.thiagosena.springkotlinbackend.payload.response.PersonResponse
import com.thiagosena.springkotlinbackend.repositories.PersonRepository
import com.thiagosena.springkotlinbackend.services.PersonService
import com.thiagosena.springkotlinbackend.transformer.AddPersonRequestTransformer
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRED
import org.springframework.transaction.annotation.Transactional

@Service
class PersonServiceImpl(
    private val repository: PersonRepository,
    private val addPersonRequestTransformer: AddPersonRequestTransformer
) : PersonService {

    override fun findById(id: Long): PersonResponse? =
        this.repository.findByIdOrNull(id)?.toPersonResponse()
            ?: throw EntityNotFoundException("Could not find a person with id $id")

    @Transactional(propagation = REQUIRED)
    override fun save(addPersonRequest: AddPersonRequest): PersonResponse =
        this.saveOrUpdate(this.addPersonRequestTransformer.transform(addPersonRequest))

    @Transactional(propagation = REQUIRED)
    override fun update(updatePersonRequest: UpdatePersonRequest): PersonResponse {
        val person =
            this.repository.findByIdOrNull(updatePersonRequest.id)
                ?: throw IllegalStateException("${updatePersonRequest.id} not found")

        return this.saveOrUpdate(person.apply {
            this.firstName = updatePersonRequest.firstName
            this.lastName = updatePersonRequest.lastName
            this.email = updatePersonRequest.email
            this.password = updatePersonRequest.password
            this.updatedAt = LocalDateTime.now()
        })
    }

    override fun findAll(pageable: Pageable): Page<PersonResponse> =
        this.repository.findAll(pageable).map(Person::toPersonResponse)

    override fun search(filter: String, pageable: Pageable): Page<PersonResponse> =
        this.repository.findAllByFirstNameIgnoreCaseOrLastNameIgnoreCase(filter, filter, pageable)
            .map(Person::toPersonResponse)

    private fun saveOrUpdate(person: Person): PersonResponse = this.repository.save(person).toPersonResponse()
}
