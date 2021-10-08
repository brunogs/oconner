package br.com.oconner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

@Document("reviews")
@CompoundIndex(def = "{ 'movieId': 1, 'author.name': 1 }", unique = true)
data class Review(
    @Id
    val id: String? = null,
    val rating: Int,
    val author: Customer,
    @Field(targetType = FieldType.STRING)
    val movieId: String
)