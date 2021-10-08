package br.com.oconner.service

import br.com.oconner.domain.Review
import br.com.oconner.repository.MovieRepository
import br.com.oconner.repository.ReviewRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class ReviewService(
        private val reviewRepository: ReviewRepository,
        private val movieRepository: MovieRepository
    ) {

    private val logger = KotlinLogging.logger {}

    fun create(review: Review) {
        logger.info { "creating review=$review" }
        reviewRepository.save(review)

        logger.info { "updating movie=${review.movieId} with aggregated rating" }
        updateReviewMovieReference(review)
    }

    private fun updateReviewMovieReference(review: Review) {
        val movieId = review.movieId
        val movie = reviewRepository.findAggregatedRating(movieId)
                .flatMap { aggregatedRating ->
                    movieRepository.findById(movieId).map { it.copy(rating = aggregatedRating) }
                }
        movie.ifPresent(movieRepository::save)
    }

}