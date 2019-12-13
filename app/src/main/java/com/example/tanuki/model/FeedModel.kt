package com.example.tanuki.model

import java.util.*

data class FeedModel(
    val id: String = "",
    val name: String = "",
    val gender: String = "",
    val goalType: String = "",
    val money : Double = 0.0,
    val date: Date = Date(),
    val message: String = "",
    val earning: Boolean = false
)