package br.com.oconner.domain

data class Review(
    val id: String? = null,
    val rating: Int,
    val author: Customer
)