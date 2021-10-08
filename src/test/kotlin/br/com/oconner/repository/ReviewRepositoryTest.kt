package br.com.oconner.repository

import br.com.oconner.fixture.Reviews
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.dao.DuplicateKeyException

@DataMongoTest
class ReviewRepositoryTest(@Autowired val reviewRepository: ReviewRepository) {

    @AfterEach
    internal fun tearDown() {
        reviewRepository.deleteAll()
    }

    @Test
    fun `should create review`() {
        val review = Reviews.createReview()

        val result = reviewRepository.save(review)

        assertNotNull(result)
        assertNotNull(result.id)
    }

    @Test
    fun `should not allow duplicated review`() {
        val reviewOne = Reviews.createReview(customerName = "User Test", movieId = "123")
        val reviewTwo = Reviews.createReview(customerName = "User Test", movieId = "123")

        reviewRepository.save(reviewOne)
        assertThrows<DuplicateKeyException> {
            reviewRepository.save(reviewTwo)
        }
    }

    @Test
    fun `should retrieve average rating`() {
        val movieId = "123"
        val reviewOne = Reviews.createReview(rating = 2, movieId = movieId)
        val reviewTwo = Reviews.createReview(rating = 5, movieId = movieId)
        val reviewThree = Reviews.createReview(rating = 1, movieId = movieId)
        val reviewFour = Reviews.createReview(rating = 3, movieId = movieId)

        listOf(reviewOne, reviewTwo, reviewThree, reviewFour).forEach(reviewRepository::save)

        val aggregatedRating = reviewRepository.findAggregatedRating(movieId).get()

        assertEquals(2, aggregatedRating.ratingAverage)
        assertEquals(4, aggregatedRating.totalReviews)
    }

}