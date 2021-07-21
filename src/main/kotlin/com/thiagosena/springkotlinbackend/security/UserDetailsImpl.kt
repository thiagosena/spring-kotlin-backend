package com.thiagosena.springkotlinbackend.security

import com.thiagosena.springkotlinbackend.models.Person
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(private val person: Person) : UserDetails {

    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun isEnabled() = true

    override fun getUsername() = person.email

    fun getPersonResponse() = person.toPersonResponse()

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = person.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}
