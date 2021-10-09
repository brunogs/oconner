package br.com.oconner.controller

import br.com.oconner.fixture.MovieTimes
import br.com.oconner.repository.MovieTimeRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class MovieTimeControllerTest(
        @Autowired val mvc: MockMvc,
        @Autowired val objectMapper: ObjectMapper,
        @Autowired val movieTimeRepository: MovieTimeRepository
) {

    @AfterEach
    fun tearDown() {
        movieTimeRepository.deleteAll()
    }

    @Test
    fun `should create movie time`() {
        val simpleMovieTime = MovieTimes.createMovieTime()

        mvc.perform(
            post("/movies/${simpleMovieTime.movieId}/times")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(simpleMovieTime))
        ).andExpect(status().isAccepted)
    }

    @Test
    fun `should update a movie time`() {
        val simpleMovieTime = MovieTimes.createMovieTime()

        mvc.perform(
            post("/movies/${simpleMovieTime.movieId}/times")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(simpleMovieTime))
        ).andExpect(status().isAccepted)

        val createdMovieTime = movieTimeRepository.findAll().first()
        val updatedMovieTime =  createdMovieTime.copy(hour = 20)

        mvc.perform(
            put("/movies/${simpleMovieTime.movieId}/times/${updatedMovieTime.id}")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedMovieTime))
        ).andExpect(status().isOk)
    }

    @ParameterizedTest
    @ValueSource(ints = [ 0, -3, 30 ])
    fun `should should validate hour`(invalidHour: Int) {
        val movieTimeWithInvalidHour = MovieTimes.createMovieTime(hour = invalidHour)

        mvc.perform(
            post("/movies/${movieTimeWithInvalidHour.movieId}/times")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieTimeWithInvalidHour))
        ).andExpect(status().isBadRequest)
    }

    @ParameterizedTest
    @ValueSource(ints = [ 0, -3, 67 ])
    fun `should should validate minutes`(invalidMinute: Int) {
        val movieTimeWithInvalidHour = MovieTimes.createMovieTime(hour = invalidMinute)

        mvc.perform(
            post("/movies/${movieTimeWithInvalidHour.movieId}/times")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieTimeWithInvalidHour))
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `should retrieve movie time`() {
        val movieId = "123"
        repeat(10) {
            movieTimeRepository.save(MovieTimes.createMovieTime(movieId = movieId, hour = it))
        }

        mvc.perform(get("/movies/${movieId}/times"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$").isArray)
            .andExpect(jsonPath("\$.length()").value(10))
            .andExpect(jsonPath("\$[0].movieId").isString)
            .andExpect(jsonPath("\$[0].dayOfWeek").isString)
            .andExpect(jsonPath("\$[0].hour").isNumber)
            .andExpect(jsonPath("\$[0].minute").isNumber)
            .andExpect(jsonPath("\$[0].auditorium").isMap)
            .andExpect(jsonPath("\$[0].price").isNumber)
    }

}