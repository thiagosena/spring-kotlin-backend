package com.thiagosena.springkotlinbackend.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app.api")
class SwaggerConfig(
    var version: String? = null,
    var title: String? = null,
    var description: String? = null,
    var contactUrl: String? = null,
    var contactName: String? = null,
    var contactEmail: String? = null
) {

    @Bean
    fun customOpenApi(): OpenAPI =
        OpenAPI().components(Components()).info(
            Info()
                .title(title)
                .description(description)
                .version(version)
                .contact(Contact().name(contactName).email(contactEmail).url(contactUrl))
        )
}
