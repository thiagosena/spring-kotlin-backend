package com.thiagosena.springkotlinbackend.services.impl

import com.thiagosena.springkotlinbackend.repositories.PersonRepository
import com.thiagosena.springkotlinbackend.security.UserDetailsImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val repository: PersonRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String?): UserDetails =
        UserDetailsImpl(this.repository.findByEmail(email) ?: throw UsernameNotFoundException(email))
}
