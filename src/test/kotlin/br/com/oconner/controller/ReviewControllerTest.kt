package br.com.oconner.controller

import br.com.oconner.domain.Customer
import br.com.oconner.fixture.Reviews
import br.com.oconner.repository.ReviewRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class ReviewControllerTest(
        @Autowired val mvc: MockMvc,
        @Autowired val objectMapper: ObjectMapper,
        @Autowired val reviewRepository: ReviewRepository
) {

    @AfterEach
    fun tearDown() {
        reviewRepository.deleteAll()
    }

    @ParameterizedTest
    @ValueSource(ints = [ 0, -3, 30 ])
    fun `should validate rating field`(invalidRating: Int) {
        val invalidReview = Reviews.createReview(rating = invalidRating)

        mvc.perform(post("/movies/123/reviews")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(invalidReview))
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `should validate author name`() {
        val invalidReview = Reviews.createReview(
                rating = 3,
                customerName = ""
        )

        mvc.perform(
                post("/movies/${invalidReview.movieId}/reviews")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidReview))
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `should create review`() {
        val simpleReview = Reviews.createReview(rating = 4)

        mvc.perform(
            post("/movies/${simpleReview.movieId}/reviews")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(simpleReview))
        ).andExpect(status().isAccepted)
    }

    @Test
    fun `should retrieve movie reviews`() {
        val movieId = "123"
        repeat(10) {
            reviewRepository.save(Reviews.createReview(movieId = movieId))
        }

        mvc.perform(get("/movies/${movieId}/reviews"))
            .andExpect(status().isOk)
                .andExpect(jsonPath("\$").isArray)
                .andExpect(jsonPath("\$.length()").value(10))
                .andExpect(jsonPath("\$[0].rating").isNumber)
                .andExpect(jsonPath("\$[0].movieId").isString)
                .andExpect(jsonPath("\$[0].author.name").isString)
    }

    @Test
    fun `should retrieve 204 for reviews absent`() {
        val movieId = "123"
        mvc.perform(get("/movies/${movieId}/reviews"))
                .andExpect(status().isNoContent)
    }

    @Test
    fun `should retrieve movie rating`() {
        val movieId = "123"
        repeat(10) {
            reviewRepository.save(Reviews.createReview(movieId = movieId))
        }

        mvc.perform(get("/movies/${movieId}/rating"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.ratingAverage").isNumber)
                .andExpect(jsonPath("\$.totalReviews").isNumber)
    }

    @Test
    fun `should retrieve 404 for absent rating`() {
        val movieId = "123"

        mvc.perform(get("/movies/${movieId}/rating"))
                .andExpect(status().isNotFound)
    }
}