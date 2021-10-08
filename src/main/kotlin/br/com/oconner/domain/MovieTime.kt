package br.com.oconner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.math.BigDecimal
import java.time.DayOfWeek
import javax.validation.Valid
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin

@Document("movie_times")
@CompoundIndex(def = "{ 'movieId': 1, 'dayOfWeek': 1, 'auditorium.name': 1, 'hour': 1 }", unique = true)
data class MovieTime(
    @Id
    val id: String? = null,

    @Field(targetType = FieldType.STRING)
    val movieId: String,

    val dayOfWeek: DayOfWeek,

    @field:DecimalMin("1")
    @field:DecimalMax("23")
    val hour: Int,

    @field:DecimalMin("1")
    @field:DecimalMax("59")
    val minute: Int,

    @Valid
    val auditorium: Auditorium,

    @field:DecimalMin("1")
    val price: BigDecimal
)