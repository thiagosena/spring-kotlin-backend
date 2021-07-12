package com.thiagosena.springkotlinbackend.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTests @Autowired constructor(
    val mockMvc: MockMvc
) {

    private val baseUrl = "/api/persons"

    @Test
    fun `should return ENTITY NOT FOUND if the person does not exist`() {
        // when/then
        mockMvc.get("$baseUrl/0")
            .andDo { print() }
            .andExpect { status { isNotFound() } }
    }

//    @Test
//    fun `post successfully person`() {
//
//        val body = """
//            {
//               "name": "Thiago Sena",
//               "cpf": "00000000000"
//            }
//        """.trimIndent()
//
//        mockMvc.post("/api/persons") {
//            contentType = MediaType.APPLICATION_JSON
//            content = body
//        }.andExpect {
//            status { isCreated() }
//        }
//    }
}
