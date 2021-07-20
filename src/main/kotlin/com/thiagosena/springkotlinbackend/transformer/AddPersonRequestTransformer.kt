package com.thiagosena.springkotlinbackend.transformer

import com.thiagosena.springkotlinbackend.models.Person
import com.thiagosena.springkotlinbackend.payload.request.AddPersonRequest
import org.springframework.stereotype.Component

@Component
class AddPersonRequestTransformer : Transformer<AddPersonRequest, Person> {
    override fun transform(source: AddPersonRequest): Person {
        val person = Person(
            firstName = source.firstName,
            lastName = source.lastName,
            email = source.email,
        )
        person.password = source.password
        return person
    }
}
