package br.com.oconner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

@Document("movie_times")
data class MovieTime(
    @Id
    val id: String? = null,
    @Field(targetType = FieldType.STRING)
    @Indexed(unique = true)
    val movieId: String,
    val times: Set<Showtime>
)