package br.com.oconner.repository

import br.com.oconner.domain.Movie
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MovieRepository : MongoRepository<Movie, String> {

    @Query(value = "{}", fields = "{ 'title': 1, 'imdbId': 1 }")
    fun findAllSummary(): List<Movie>

    fun findByImdbId(imdbId: String): Optional<Movie>

    fun findByIdAndDetailsIsTrue(imdbId: String): Optional<Movie>

}