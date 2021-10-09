package br.com.oconner.service

import br.com.oconner.config.SeedConfig
import br.com.oconner.repository.MovieRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class MovieService(
    private val movieRepository: MovieRepository,
    private val seedConfig: SeedConfig
) {

    private val logger = KotlinLogging.logger {}

    @PostConstruct
    fun seedCatalogMovies() {
        logger.info { "seed catalog movies" }

        seedConfig.movieCatalog.forEach {
            movieRepository.findByImdbId(it.imdbId)
                .ifPresentOrElse(
                    { logger.info { "movie ${it.imdbId} already exists" } },
                    {
                        logger.info { "creating movie ${it.imdbId}" }
                        movieRepository.save(it)
                    }
                )
        }
    }

    fun getMovies() =
        movieRepository.findAllSummary()
}