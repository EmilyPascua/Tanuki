package com.example.tanuki.model

data class FeedModel(
    val id: Int,
    val date: String,
    val month: Int,
    val day: Int,
    val year: Int,
    val message: String
)