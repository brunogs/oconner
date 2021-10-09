package br.com.oconner.controller

import br.com.oconner.service.MovieService
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/movies")
@Api(value = "Movies", produces = MediaType.APPLICATION_JSON_VALUE, tags = ["movies-endpoints"], description = "O'Conner API")
class MovieController(
    private val movieService: MovieService
) {

    @GetMapping
    fun getMovies() = movieService.getMovies()

    @GetMapping("/{movieId}")
    fun getMovieById(@PathVariable movieId: String) =
        movieService.getMovieById(movieId).orElseThrow {
            ResponseStatusException(NOT_FOUND)
        }

}