package br.com.oconner.repository

import br.com.oconner.domain.AggregatedRating
import br.com.oconner.domain.Review
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ReviewRepository : MongoRepository<Review, String>, ReviewCustomRepository {

    fun findAllByMovieId(movieId: String): List<Review>

}

interface ReviewCustomRepository {
    fun findAggregatedRating(movieId: String) : Optional<AggregatedRating>
}