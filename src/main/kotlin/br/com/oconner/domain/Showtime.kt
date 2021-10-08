package br.com.oconner.domain

import java.math.BigDecimal
import java.time.DayOfWeek

data class Showtime(
    val dayOfWeek: DayOfWeek,
    val hour: Int,
    val minute: Int,
    val auditorium: Auditorium,
    val price: BigDecimal
)