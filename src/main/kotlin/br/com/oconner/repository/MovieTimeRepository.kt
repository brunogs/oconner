package br.com.oconner.repository

import br.com.oconner.domain.MovieTime
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieTimeRepository : MongoRepository<MovieTime, String> {
}