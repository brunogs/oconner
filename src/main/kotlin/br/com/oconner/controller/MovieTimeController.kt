package br.com.oconner.controller

import br.com.oconner.domain.MovieTime
import br.com.oconner.domain.Review
import br.com.oconner.service.MovieTimeService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/movies")
class MovieTimeController(
    private val movieTimeService: MovieTimeService
) {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{movieId}/times")
    fun createMovieTime(@PathVariable movieId: String, @Valid @RequestBody movieTime: MovieTime) {
        movieTimeService.create(movieTime)
    }

    @PutMapping("/{movieId}/times")
    fun putMovieTime(@PathVariable movieId: String, @Valid @RequestBody movieTime: MovieTime) {
        movieTimeService.create(movieTime)
    }

    @GetMapping("/{movieId}/times")
    fun getMovieTimes(@PathVariable movieId: String) =
        movieTimeService.getMovieTimesByMovieId(movieId).let {
            when {
                it.isNotEmpty() -> ResponseEntity.ok(it)
                else -> ResponseEntity.noContent().build()
            }
        }

}