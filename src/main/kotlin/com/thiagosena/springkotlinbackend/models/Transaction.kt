package com.thiagosena.springkotlinbackend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "transaction")
class Transaction(
    @Id
    @SequenceGenerator(name = TRANSACTION_SEQUENCE, sequenceName = TRANSACTION_SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = TRANSACTION_SEQUENCE)
    @Column(name = "id")
    val id: Long,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    val person: Person,

    @Column(name = "source_currency")
    val sourceCurrency: String,

    @Column(name = "source_value")
    val sourceValue: BigDecimal,

    @Column(name = "target_currency")
    val targetCurrency: String,

    @Column(name = "conversion_rate")
    val conversionRate: BigDecimal,

    @Column(name = "created_at")
    val createdAt: LocalDateTime
) {
    companion object {
        const val TRANSACTION_SEQUENCE: String = "TRANSACTION_SEQUENCE"
    }
}
