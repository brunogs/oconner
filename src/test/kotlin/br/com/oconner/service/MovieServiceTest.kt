package br.com.oconner.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieServiceTest(
    @Autowired private val movieService: MovieService
) {

    @Test
    fun `should seed catalog movie from configuration`() {
        val movies = movieService.getMovies()

        assertEquals(8, movies.size)
    }
}