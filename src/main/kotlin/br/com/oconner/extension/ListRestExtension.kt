package br.com.oconner.extension

import org.springframework.http.ResponseEntity

fun <T> List<T>.orElseNoContent() = this.let {
    when {
        it.isNotEmpty() -> ResponseEntity.ok(it)
        else -> ResponseEntity.noContent().build()
    }
}