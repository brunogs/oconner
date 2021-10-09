package br.com.oconner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document("movies")
data class Movie (
    @Id
    val id: String? = null,
    val title: String,
    val releaseDate: String? = null,
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
    val imdbRating: String? = null,
    val imdbVotes: String? = null,
    @Indexed
    val imdbId: String,
    val type: String? = null,
    val dvd: String? = null,
    val boxOffice: String? = null,
    val production: String? = null,
    val website: String? = null,
    val ratings: Set<Rating> = setOf(),
    val details: Boolean = false
)