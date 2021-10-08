package br.com.oconner.domain

import javax.validation.constraints.NotBlank

data class Customer(
    @field:NotBlank
    val name: String
)