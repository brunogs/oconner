package br.com.oconner.fixture

import br.com.oconner.domain.AggregatedRating
import br.com.oconner.domain.Movie
import java.time.LocalDate

object Movies {

    val validMovie = Movie(
        name = "The Fast and the Furious",
        description = "",
        releaseDate = LocalDate.of(2001, 6, 22),
        rating = AggregatedRating(ratingAverage = 5, totalReviews = 1),
        imdbRating = "",
        runtime = "106 min"
    )
}