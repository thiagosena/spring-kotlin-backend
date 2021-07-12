package com.thiagosena.springkotlinbackend.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.cors()
            .configurationSource { CorsConfiguration().applyPermitDefaultValues() }
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
}
