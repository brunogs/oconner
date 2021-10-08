package br.com.oconner.service

import br.com.oconner.domain.MovieTime
import br.com.oconner.repository.MovieTimeRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class MovieTimeService(
    private val movieTimeRepository: MovieTimeRepository
) {
    private val logger = KotlinLogging.logger {}

    fun create(movieTime: MovieTime) {
        logger.info { "creating movieTime=$movieTime" }
        movieTimeRepository.save(movieTime)
    }

    fun getMovieTimesByMovieId(movieId: String) =
        movieTimeRepository.findAllByMovieId(movieId)
}