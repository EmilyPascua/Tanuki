package com.example.tanuki.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var uid: String = "",
    var name: String = "",
    var gender: String = "",
    var email: String = "",
    var payments: ArrayList<PaymentEntity.Payment> = ArrayList(),
    var budget: Double = 0.0,
    var budgetType: String = ""
)
