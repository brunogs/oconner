package br.com.oconner.repository

import br.com.oconner.domain.omdb.ExternalMovie
import mu.KotlinLogging
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient

@Repository
class OMDBRepository(
    private val omdbWebClient: WebClient
) {
    private val logger = KotlinLogging.logger {}

    companion object {
        const val ID_PARAMETER = "i"
    }

    fun getMovieById(movieId: String) =
        omdbWebClient.get()
            .uri { uriBuilder -> uriBuilder
                .queryParam(ID_PARAMETER, movieId)
                .build()
            }
            .retrieve()
            .bodyToMono(ExternalMovie::class.java)
            .doOnError {
                logger.warn(it) { "something wrong requesting omdb" }
            }
            .block()

}