package com.example.tanuki.model

import java.util.*

class PaymentEntity {
    data class Payment(
        val id: Int,
        val date: Date,
        val payment: Double,
        val type: String,
        var user: String,

        val iconStr: Int,
        val backgroundStr: Int,

        val isBill: Boolean
    )
}