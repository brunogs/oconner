package br.com.oconner.repository

import br.com.oconner.domain.Customer
import br.com.oconner.domain.Review
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class ReviewRepositoryTest(@Autowired val reviewRepository: ReviewRepository) {

    @Test
    fun `should create review`() {
        val review = Review(
            rating = 5,
            author = Customer(name = "User Test"),
            movieId = "123"
        )

        val result = reviewRepository.save(review)

        assertNotNull(result)
        assertNotNull(result.id)
    }
}