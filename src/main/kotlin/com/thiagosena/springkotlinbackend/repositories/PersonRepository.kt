package com.thiagosena.springkotlinbackend.repositories

import com.thiagosena.springkotlinbackend.models.Person
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long> {
//    @Query("select p from #{#entityName} p where p.name like %:filter%")
//    fun search(filter: String, sort: Sort): MutableIterable<Person>

    fun findAllByFirstName(name: String, pageable: Pageable?): Page<Person>

    fun findByEmail(email: String?): Person?
}
