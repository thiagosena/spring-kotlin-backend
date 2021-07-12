package com.thiagosena.springkotlinbackend.services

import com.thiagosena.springkotlinbackend.repositories.PersonRepository
import com.thiagosena.springkotlinbackend.services.impl.PersonServiceImpl
import com.thiagosena.springkotlinbackend.transformer.AddPersonRequestTransformer
import com.thiagosena.springkotlinbackend.wrappers.response.PersonResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

internal class PersonServiceTest {

    private val personRepository: PersonRepository = mockk()
    private val addPersonRequestTransformer: AddPersonRequestTransformer = mockk(relaxed = true)
    private val personService: PersonService = PersonServiceImpl(personRepository, addPersonRequestTransformer)

    @Test
    fun `should call its repository to retrieve a person response by id`() {
        // when
        every { personRepository.findByIdOrNull(1)?.toPersonResponse() } returns PersonResponse(1, "", "")
        personService.findById(1)

        // then
        verify(exactly = 1) { personRepository.findByIdOrNull(1)?.toPersonResponse() }
    }
}
