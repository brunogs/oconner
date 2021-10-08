package br.com.oconner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

@Document("reviews")
data class Review(
    @Id
    val id: String? = null,
    val rating: Int,
    val author: Customer,
    @Field(targetType = FieldType.STRING)
    val movieId: String
)