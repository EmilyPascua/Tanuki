package com.example.tanuki.model

import java.util.*

data class FeedModel(
    val id: Int,
    val name: String,
    val money : Int,
    val date: Date,
    val message: String,
    val earning: Boolean
)