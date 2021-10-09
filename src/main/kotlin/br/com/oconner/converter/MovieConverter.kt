package br.com.oconner.converter

import br.com.oconner.domain.Movie
import br.com.oconner.domain.Rating
import br.com.oconner.domain.omdb.ExternalMovie
import br.com.oconner.domain.omdb.ExternalRating

object MovieConverter {

    fun fromExternalMovie(externalMovie: ExternalMovie) = Movie(
        title = externalMovie.title!!,
        releaseDate = externalMovie.released,
        runtime = externalMovie.runtime,
        genre = externalMovie.genre,
        director = externalMovie.director,
        writer = externalMovie.writer,
        actors = externalMovie.actors,
        plot = externalMovie.plot,
        language = externalMovie.language,
        country = externalMovie.country,
        awards = externalMovie.awards,
        poster = externalMovie.poster,
        metascore = externalMovie.metascore,
        imdbRating = externalMovie.imdbRating,
        imdbVotes = externalMovie.imdbVotes,
        imdbId = externalMovie.imdbId!!,
        type = externalMovie.type,
        dvd = externalMovie.dvd,
        boxOffice = externalMovie.boxOffice,
        production = externalMovie.production,
        website = externalMovie.website,
        ratings = externalMovie.ratings?.map(this::fromExternalRating)?.toSet() ?: setOf(),
        details = true
    )

    private fun fromExternalRating(externalRating: ExternalRating) = Rating(
        source = externalRating.source,
        value = externalRating.value
    )

}