package com.thiagosena.springkotlinbackend.models

import com.thiagosena.springkotlinbackend.wrappers.response.PersonResponse
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
@Table(name = "person")
class Person(
    @Id
    @SequenceGenerator(name = PERSON_SEQUENCE, sequenceName = PERSON_SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = PERSON_SEQUENCE)
    @Column(name = "id")
    var id: Long = 0,

    @NotNull
    @Size(min = 2, max = 128)
    @Column(name = "firstname")
    var firstName: String,

    @NotNull
    @Size(min = 2, max = 128)
    @Column(name = "lastname")
    var lastName: String,

    @NotNull
    @Email
    @Column(name = "email")
    var email: String = "",

    @Column(name = "created_at", columnDefinition = "timestamp")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", columnDefinition = "timestamp")
    var updatedAt: LocalDateTime? = null
) {
    @NotNull
    var password: String = ""
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    fun toPersonResponse(): PersonResponse {
        return PersonResponse(
            id = this.id,
            email = this.email,
            fullName = "${this.lastName}, ${this.firstName}"
        )
    }

    companion object {
        const val PERSON_SEQUENCE: String = "PERSON_SEQUENCE"
    }
}
