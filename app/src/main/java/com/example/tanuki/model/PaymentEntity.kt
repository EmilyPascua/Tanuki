package com.example.tanuki.model

class PaymentEntity {
    data class Payment(
        val id: Int,
        val month: Int,
        val day: Int,
        val year: Int,
        val payment: Double,
        val type: String,

        val iconStr: String,
        val backgroundStr: String,

        val isBill: Boolean
    )
}