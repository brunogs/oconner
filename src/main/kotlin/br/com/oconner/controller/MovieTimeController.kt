package br.com.oconner.controller

import br.com.oconner.domain.MovieTime
import br.com.oconner.service.MovieTimeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

import br.com.oconner.extension.orElseNoContent
import io.swagger.annotations.Api
import org.springframework.http.MediaType

@RestController
@RequestMapping("/movies")
@Api(value = "Movies", produces = MediaType.APPLICATION_JSON_VALUE, tags = ["movies-endpoints"], description = "O'Conner API")
class MovieTimeController(
    private val movieTimeService: MovieTimeService
) {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{movieId}/times")
    fun createMovieTime(@PathVariable movieId: String, @Valid @RequestBody movieTime: MovieTime) {
        movieTimeService.create(movieTime)
    }

    @PutMapping("/{movieId}/times/{movieTimeId}")
    fun putMovieTime(@PathVariable movieId: String, @PathVariable movieTimeId: String,
                     @Valid @RequestBody movieTime: MovieTime) {
        movieTimeService.update(movieTime.copy(id = movieTimeId))
    }

    @GetMapping("/{movieId}/times")
    fun getMovieTimes(@PathVariable movieId: String) =
        movieTimeService.getMovieTimesByMovieId(movieId).orElseNoContent()

}