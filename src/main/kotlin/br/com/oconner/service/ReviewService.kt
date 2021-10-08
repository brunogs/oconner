package br.com.oconner.service

import br.com.oconner.domain.Review
import br.com.oconner.repository.ReviewRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class ReviewService(
        private val reviewRepository: ReviewRepository
    ) {

    private val logger = KotlinLogging.logger {}

    fun create(review: Review) {
        logger.info { "creating review=$review" }
        reviewRepository.save(review)
    }

}