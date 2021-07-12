package com.thiagosena.springkotlinbackend.services

import java.math.BigDecimal

interface TransactionService {
    fun convert(userId: Long, source: String, target: String, value: BigDecimal)
}
