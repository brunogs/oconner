package br.com.oconner.fixture

import br.com.oconner.domain.Customer
import br.com.oconner.domain.Review
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils

object Reviews {

    fun createReview(
            customerName: String = RandomStringUtils.randomAlphabetic(5),
            rating: Int = RandomUtils.nextInt(),
            movieId: String = RandomStringUtils.randomAlphabetic(5)) : Review {
        return Review(
                rating = rating,
                author = Customer(name = customerName),
                movieId = movieId
        )
    }
}