package br.com.oconner.repository

import br.com.oconner.fixture.Movies
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class MovieRepositoryTest(@Autowired val movieRepository: MovieRepository) {

    @AfterEach
    internal fun tearDown() {
        movieRepository.deleteAll()
    }

    @Test
    fun `should create movie`() {
        val movie = Movies.validMovie

        val result = movieRepository.save(movie)

        assertNotNull(result)
        assertNotNull(result.id)
    }

    @Test
    fun `should retrieve summary movies`() {
        val movie = Movies.validMovie

        movieRepository.save(movie)

        val summary = movieRepository.findAllSummary()

        assertNotNull(summary)
        assertNotNull(summary.first().title)
        assertNotNull(summary.first().imdbId)
    }
}