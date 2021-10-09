package br.com.oconner.service

import br.com.oconner.config.SeedConfig
import br.com.oconner.domain.Movie
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
        val imdbId = "321"

        every { movieRepository.findById(movieId) } returns Optional.of(Movie(title = "", imdbId = imdbId))
        every { omdbRepository.getMovieById(imdbId) } returns ExternalMovie(title = "", imdbId = imdbId)
        every { movieRepository.save(any()) } returns Movies.validMovie

        val service = MovieService(movieRepository, omdbRepository, seedConfig)

        service.getMovieById(movieId)

        verify(exactly = 1) { movieRepository.findById(movieId) }
        verify(exactly = 1) { omdbRepository.getMovieById(imdbId) }
        verify(exactly = 1) { movieRepository.save(any()) }
    }
}