package br.com.oconner.domain.omdb

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy::class)
data class ExternalMovie (
    val title: String? = null,
    val year: String? = null,
    val rated: String? = null,
    val released: String? = null,
    val runtime: String? = null,
    val genre: String? = null,
    val director: String? = null,
    val writer: String? = null,
    val actors: String? = null,
    val plot: String? = null,
    val language: String? = null,
    val country: String? = null,
    val awards: String? = null,
    val poster: String? = null,
    val metascore: String? = null,
    @JsonProperty("imdbRating")
    val imdbRating: String? = null,
    @JsonProperty("imdbVotes")
    val imdbVotes: String? = null,
    @JsonProperty("imdbID")
    val imdbId: String? = null,
    val type: String? = null,
    @JsonProperty("DVD")
    val dvd: String? = null,
    val boxOffice: String? = null,
    val production: String? = null,
    val website: String? = null,
    val response: String? = null,
    val ratings: Set<ExternalRating>? = setOf()
)