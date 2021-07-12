package com.thiagosena.springkotlinbackend.services.impl

import com.thiagosena.springkotlinbackend.services.TransactionService
import java.math.BigDecimal

class TransactionServiceImpl : TransactionService {
    override fun convert(userId: Long, source: String, target: String, value: BigDecimal) {
        TODO("Not yet implemented")
    }
}
