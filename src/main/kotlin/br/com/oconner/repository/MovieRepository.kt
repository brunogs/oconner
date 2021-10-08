package br.com.oconner.repository

import br.com.oconner.domain.Movie
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : MongoRepository<Movie, String> {
}