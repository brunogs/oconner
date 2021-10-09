package br.com.oconner

import br.com.oconner.config.SeedConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(SeedConfig::class)
class OconnerApplication

fun main(args: Array<String>) {
	runApplication<OconnerApplication>(*args)
}
