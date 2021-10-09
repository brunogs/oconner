package br.com.oconner.service

import br.com.oconner.config.SeedConfig
import br.com.oconner.converter.MovieConverter
import br.com.oconner.repository.MovieRepository
import br.com.oconner.repository.OMDBRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class MovieService(
    private val movieRepository: MovieRepository,
    private val omdbRepository: OMDBRepository,
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

    fun getMovieById(imdbId: String) =
        movieRepository.findByImdbIdAndDetailsIsTrue(imdbId)
            .or { fetchExternal(imdbId) }

    private fun fetchExternal(imdbId: String) = try {
        val externalMovie = omdbRepository.getMovieById(imdbId)
        val updatedMovie = movieRepository.save(MovieConverter.fromExternalMovie(externalMovie!!))
        Optional.of(updatedMovie)
    } catch (e: Exception ) {
        logger.error(e) { "something wrong fetching details" }
        Optional.empty()
    }

}