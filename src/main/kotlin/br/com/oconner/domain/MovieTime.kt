package br.com.oconner.domain

class MovieTime(
    val id: String? = null,
    val movie: Movie,
    val times: Set<Showtime>
)