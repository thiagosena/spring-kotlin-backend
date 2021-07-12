package com.thiagosena.springkotlinbackend.models

import java.math.BigDecimal
import java.time.LocalDateTime

data class Transaction(
    val id: Long,
    val person: Person,
    val sourceCurrency: String,
    val sourceValue: BigDecimal,
    val targetCurrency: String,
    val conversionRate: BigDecimal,
    val dateTime: LocalDateTime
)
