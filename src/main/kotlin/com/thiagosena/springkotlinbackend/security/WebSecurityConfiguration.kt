package com.thiagosena.springkotlinbackend.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/persons").permitAll()
            .antMatchers(
                "/api-docs/**",
                "/actuator/**",
                "/swagger-ui-custom.html",
                "/swagger-ui/**",
                "/webjars/**"
            ).permitAll()
            .anyRequest().authenticated()
        http.addFilter(JWTAuthenticationFilter(authenticationManager(), jwtUtil = jwtUtil))
        http.addFilter(
            JWTAuthorizationFilter(
                authenticationManager(),
                jwtUtil = jwtUtil,
                userDetailService = userDetailsService
            )
        )
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    /**
     * Password encryption algorithm
     *
     * @return
     */
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }
}
