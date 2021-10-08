package br.com.oconner.controller

import br.com.oconner.domain.Review
import br.com.oconner.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.ACCEPTED
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.ok
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/movies")
class ReviewController(private val reviewService: ReviewService) {

    @ResponseStatus(ACCEPTED)
    @PostMapping("/{movieId}/reviews")
    fun createReview(@PathVariable movieId: String, @Validated @RequestBody review: Review) {
        reviewService.create(review)
    }

    @GetMapping("/{movieId}/reviews")
    fun getReviews(@PathVariable movieId: String) =
         reviewService.getReviewByMovieId(movieId).let {
            when {
                it.isNotEmpty() -> ok(it)
                else -> noContent().build()
            }
        }


    @GetMapping("/{movieId}/rating")
    fun getRating(@PathVariable movieId: String) =
        reviewService.getRatingByMovieId(movieId).orElseThrow {
            ResponseStatusException(NOT_FOUND)
        }

}