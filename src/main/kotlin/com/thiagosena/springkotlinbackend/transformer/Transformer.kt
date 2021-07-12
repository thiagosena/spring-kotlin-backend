package com.thiagosena.springkotlinbackend.transformer

interface Transformer<A, B> {
    fun transform(source: A): B
}
