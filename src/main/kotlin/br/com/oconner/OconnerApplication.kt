package br.com.oconner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OconnerApplication

fun main(args: Array<String>) {
	runApplication<OconnerApplication>(*args)
}
