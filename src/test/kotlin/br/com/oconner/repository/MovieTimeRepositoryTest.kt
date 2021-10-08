package br.com.oconner.repository

import br.com.oconner.domain.Auditorium
import br.com.oconner.domain.MovieTime
import br.com.oconner.domain.Showtime
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.dao.DuplicateKeyException
import java.math.BigDecimal
import java.time.DayOfWeek

@DataMongoTest
class MovieTimeRepositoryTest(@Autowired val movieTimeRepository: MovieTimeRepository) {

    @AfterEach
    internal fun tearDown() {
        movieTimeRepository.deleteAll()
    }

    @Test
    fun `should create movie time`() {
        val movieTime = MovieTime(
            movieId = "123",
            dayOfWeek = DayOfWeek.MONDAY,
            hour = 15,
            minute = 30,
            auditorium = Auditorium("Auditorium 1"),
            price = BigDecimal("15.00")
        )

        val result = movieTimeRepository.save(movieTime)

        assertNotNull(result)
        assertNotNull(result.id)
    }

    @Test
    fun `should not allow two movie time for same movie`() {
        val movieTime = MovieTime(
                movieId = "123",
                dayOfWeek = DayOfWeek.MONDAY,
                hour = 15,
                minute = 30,
                auditorium = Auditorium("Auditorium 1"),
                price = BigDecimal("15.00")
        )
        val movieTimeTwo = MovieTime(
                movieId = "123",
                dayOfWeek = DayOfWeek.MONDAY,
                hour = 15,
                minute = 30,
                auditorium = Auditorium("Auditorium 1"),
                price = BigDecimal("15.00")
        )

        movieTimeRepository.save(movieTime)

        assertThrows<DuplicateKeyException> {
            movieTimeRepository.save(movieTimeTwo)
        }
    }
}