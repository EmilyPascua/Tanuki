package com.example.tanuki.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var uid: String,
    var name: String,
    var email: String,
    var weeklyBudget: Float,
    var monthlyBudget: Float
)  {

}
