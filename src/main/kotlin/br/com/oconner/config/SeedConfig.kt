package br.com.oconner.config

import br.com.oconner.domain.Movie
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties

@ConstructorBinding
@ConfigurationProperties(prefix = "custom.seed")
data class SeedConfig (
    val movieCatalog: List<Movie>
)