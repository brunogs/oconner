package br.com.oconner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("movie_times")
data class MovieTime(
    @Id
    val id: String? = null,
    val movieId: String,
    val times: Set<Showtime>
)