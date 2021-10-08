package br.com.oconner.domain

import java.time.LocalDate

data class Movie (
    val id: String? = null,
    val name: String,
    val description: String,
    val releaseDate: LocalDate,
    val rating: Review,
    val imdbRating: String,
    val runtime: String
)