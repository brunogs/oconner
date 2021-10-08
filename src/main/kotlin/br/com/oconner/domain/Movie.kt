package br.com.oconner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document("movies")
data class Movie (
    @Id
    val id: String? = null,
    val name: String,
    val description: String,
    val releaseDate: LocalDate,
    val rating: Int,
    val imdbRating: String,
    val runtime: String
)