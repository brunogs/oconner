package br.com.oconner.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import javax.validation.constraints.NotEmpty

@Configuration
@ConfigurationProperties(prefix = "custom.omdb")
class ClientConfig {

    @NotEmpty
    lateinit var url: String

    @Bean
    fun omdbWebClient() = WebClient.builder()
        .baseUrl(url)
        .build()
}