package br.com.oconner.domain.omdb

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy::class)
data class ExternalRating(
    val source: String,
    val value: String
)