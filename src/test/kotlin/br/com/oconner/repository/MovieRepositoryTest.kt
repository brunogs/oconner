package br.com.oconner.repository

import br.com.oconner.domain.Movie
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import java.time.LocalDate

@DataMongoTest
class MovieRepositoryTest(@Autowired val movieRepository: MovieRepository) {

    @Test
    fun `should create movie`() {
        val movie = Movie(
                name="The Fast and the Furious",
                description="",
                releaseDate = LocalDate.of(2001, 6, 22),
                rating = 5,
                imdbRating = "",
                runtime = "106 min"
        )

        val result = movieRepository.save(movie)

        assertNotNull(result)
        assertNotNull(result.id)
    }
}