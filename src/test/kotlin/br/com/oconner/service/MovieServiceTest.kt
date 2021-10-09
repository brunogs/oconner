package br.com.oconner.service

import br.com.oconner.config.SeedConfig
import br.com.oconner.domain.omdb.ExternalMovie
import br.com.oconner.fixture.Movies
import br.com.oconner.repository.MovieRepository
import br.com.oconner.repository.OMDBRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieServiceTest(
    @Autowired private val movieService: MovieService
) {

    @Test
    fun `should seed catalog movie from configuration`() {
        val movies = movieService.getMovies()

        assertEquals(8, movies.size)
    }

    @Test
    fun `should fetch external movie`() {
        val movieRepository: MovieRepository = mockk()
        val omdbRepository: OMDBRepository = mockk()
        val seedConfig: SeedConfig = mockk()

        val movieId = "123"

        every { movieRepository.findByImdbIdAndDetailsIsTrue(movieId) } returns Optional.empty()
        every { omdbRepository.getMovieById(movieId) } returns ExternalMovie(title = "", imdbId = "")
        every { movieRepository.save(any()) } returns Movies.validMovie

        val service = MovieService(movieRepository, omdbRepository, seedConfig)

        service.getMovieById(movieId)

        verify(exactly = 1) { movieRepository.findByImdbIdAndDetailsIsTrue(movieId) }
        verify(exactly = 1) { omdbRepository.getMovieById(movieId) }
        verify(exactly = 1) { movieRepository.save(any()) }
    }
}