package br.com.oconner.controller

import br.com.oconner.domain.Review
import br.com.oconner.service.ReviewService
import org.springframework.http.HttpStatus.ACCEPTED
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

import br.com.oconner.extension.orElseNoContent

@RestController
@RequestMapping("/movies")
class ReviewController(private val reviewService: ReviewService) {

    @ResponseStatus(ACCEPTED)
    @PostMapping("/{movieId}/reviews")
    fun createReview(@PathVariable movieId: String, @Valid @RequestBody review: Review) {
        reviewService.create(review)
    }

    @GetMapping("/{movieId}/reviews")
    fun getReviews(@PathVariable movieId: String) =
         reviewService.getReviewByMovieId(movieId).orElseNoContent()


    @GetMapping("/{movieId}/rating")
    fun getRating(@PathVariable movieId: String) =
        reviewService.getRatingByMovieId(movieId).orElseThrow {
            ResponseStatusException(NOT_FOUND)
        }

}