package com.thiagosena.springkotlinbackend.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import java.util.Optional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("info.app")
class SwaggerConfig(
    var version: String? = null,
    var title: String? = null,
    var description: String? = null,
    var contactUrl: String? = null,
    var contactName: String? = null,
    var contactEmail: String? = null
) {

    @Autowired
    lateinit var build: Optional<BuildProperties>

    @Bean
    fun customOpenApi(): OpenAPI =
        OpenAPI().components(
            Components()
                .addSecuritySchemes(
                    "bearer-key",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        ).info(
            Info()
                .title(title)
                .description(description)
                .version(build.get().version)
                .contact(Contact().name(contactName).email(contactEmail).url(contactUrl))
        )
}
