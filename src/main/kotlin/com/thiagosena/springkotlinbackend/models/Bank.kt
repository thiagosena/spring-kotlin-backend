package com.thiagosena.springkotlinbackend.models

data class Bank(
    val id: Long,
    val accountNumber: String,
    val trust: Double,
    val transactionFee: Int
)
