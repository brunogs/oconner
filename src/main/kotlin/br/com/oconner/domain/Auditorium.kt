package br.com.oconner.domain

import javax.validation.constraints.NotBlank

data class Auditorium(
    @field:NotBlank
    val name: String
)