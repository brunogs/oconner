package br.com.oconner.service

import br.com.oconner.domain.AggregatedRating
import br.com.oconner.fixture.Movies
import br.com.oconner.fixture.Reviews
import br.com.oconner.repository.MovieRepository
import br.com.oconner.repository.ReviewRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReviewServiceTest(
    @Autowired val reviewService: ReviewService,
    @Autowired val reviewRepository: ReviewRepository,
    @Autowired val movieRepository: MovieRepository) {

    @AfterEach
    internal fun tearDown() {
        reviewRepository.deleteAll()
        movieRepository.deleteAll()
    }

    @Test
    fun `should create review and update movie reference`() {
        val movie = Movies.validMovie.copy(rating = AggregatedRating(0, 0))

        val movieCreated = movieRepository.save(movie)

        val review = Reviews.createReview(movieId = movieCreated.id!!)
        reviewService.create(review)

        val movieUpdated = movieRepository.findById(movieCreated.id!!).get()

        assertEquals(review.rating, movieUpdated.rating.ratingAverage)
        assertEquals(1, movieUpdated.rating.totalReviews)
    }
}