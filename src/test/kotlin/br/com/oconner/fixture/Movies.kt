package br.com.oconner.fixture

import br.com.oconner.domain.Movie
import java.time.LocalDate

object Movies {

    val validMovie = Movie(
        title = "The Fast and the Furious",
        imdbId = "1234",
        releaseDate = LocalDate.of(2001, 6, 22),
        imdbRating = "",
        runtime = "106 min"
    )
}