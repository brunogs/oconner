package br.com.oconner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("reviews")
data class Review(
    @Id
    val id: String? = null,
    val rating: Int,
    val author: Customer,
    val movieId: String
)