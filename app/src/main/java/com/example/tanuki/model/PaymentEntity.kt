package com.example.tanuki.model

import java.util.*

class PaymentEntity {
    data class Payment(
        val id: String = "", //ownerid
        val date: Date = Date(), //date payed
        val amount: Double = 0.0,
        val type: String = "",
        val iconStr: Int = 0,
        val backgroundStr: Int = 0,
        val isBill: Boolean = false,
        val message: String = ""
    )
}