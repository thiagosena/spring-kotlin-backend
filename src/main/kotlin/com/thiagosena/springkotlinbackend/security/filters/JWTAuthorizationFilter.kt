package com.thiagosena.springkotlinbackend.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.thiagosena.springkotlinbackend.AUTHORIZATION
import com.thiagosena.springkotlinbackend.BEARER
import com.thiagosena.springkotlinbackend.wrappers.response.error.ErrorMessage
import com.thiagosena.springkotlinbackend.wrappers.response.error.ErrorResponse
import io.jsonwebtoken.ExpiredJwtException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class JWTAuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private var jwtUtil: JWTUtil,
    private var userDetailService: UserDetailsService
) : BasicAuthenticationFilter(authenticationManager) {

    private val objectMapper = ObjectMapper().registerModule(KotlinModule())

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = request.getHeader(AUTHORIZATION)

        try {
            if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
                val auth = getAuthentication(authorizationHeader)
                SecurityContextHolder.getContext().authentication = auth
            }

            chain.doFilter(request, response)
        } catch (e: ExpiredJwtException) {
            val responseEntity = ErrorResponse(
                ErrorMessage(
                    e.localizedMessage,
                    UNAUTHORIZED,
                    UNAUTHORIZED.value()
                )
            )

            objectMapper.findAndRegisterModules()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write(objectMapper.writeValueAsString(responseEntity))
        }
    }

    private fun getAuthentication(authorizationHeader: String): UsernamePasswordAuthenticationToken {
        val token = authorizationHeader.substring(7)
        if (jwtUtil.isTokenValid(token)) {
            val username = jwtUtil.getUserName(token)
            val user = userDetailService.loadUserByUsername(username)
                ?: throw UsernameNotFoundException("User not found")
            return UsernamePasswordAuthenticationToken(user, null, user.authorities)
        }
        throw UsernameNotFoundException("Auth invalid!")
    }

}
