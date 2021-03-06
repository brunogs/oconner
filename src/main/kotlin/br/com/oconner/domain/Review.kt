package br.com.oconner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import javax.validation.Valid
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotBlank

@Document("reviews")
@CompoundIndex(def = "{ 'movieId': 1, 'author.name': 1 }", unique = true)
data class Review(
    @Id
    val id: String? = null,

    @field:DecimalMin("1")
    @field:DecimalMax("5")
    val rating: Int,

    @field:Valid
    val author: Customer,

    @Indexed
    @Field(targetType = FieldType.STRING)
    @field:NotBlank
    val movieId: String
)