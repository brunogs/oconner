package br.com.oconner.fixture

import br.com.oconner.domain.Auditorium
import br.com.oconner.domain.MovieTime
import org.apache.commons.lang3.RandomStringUtils
import java.math.BigDecimal
import java.time.DayOfWeek

object MovieTimes {

    fun createMovieTime(
        movieId: String = RandomStringUtils.randomAlphabetic(5),
        hour: Int = 12,
        minute: Int = 30
    ) = MovieTime(
          movieId = movieId,
          dayOfWeek = DayOfWeek.MONDAY,
          hour = hour,
          minute = minute,
          auditorium = Auditorium(name =RandomStringUtils.randomAlphabetic(10) ),
          price = BigDecimal.TEN
    )
}