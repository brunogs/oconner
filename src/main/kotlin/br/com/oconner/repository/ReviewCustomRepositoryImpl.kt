package br.com.oconner.repository

import br.com.oconner.domain.AggregatedRating
import br.com.oconner.domain.Review
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.Aggregation.group
import org.springframework.data.mongodb.core.aggregation.Aggregation.match
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ReviewCustomRepositoryImpl(val mongoTemplate: MongoTemplate) : ReviewCustomRepository {

    override fun findAggregatedRating(movieId: String): Optional<AggregatedRating> {
        val aggregationResults = mongoTemplate.aggregate(
            Aggregation.newAggregation(
                match(where("movieId").`is`(movieId)),
                group()
                    .count().`as`("totalReviews")
                    .avg("\$rating").`as`("ratingAverage")
            ),
            Review::class.java,
            AggregatedRating::class.java
        ).mappedResults

        return when {
            aggregationResults.isNotEmpty() -> Optional.of(aggregationResults.first())
            else -> Optional.empty()
        }
    }

}