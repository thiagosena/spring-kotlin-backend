package com.thiagosena.springkotlinbackend.models

import java.math.BigDecimal
import java.time.LocalDate

data class DomesticBill(
    var id: Long,
    var description: String,
    var dueDate: LocalDate,
    var value: BigDecimal,
    var person: Person
)
