package com.example.tanuki.model

object BudgetModel {
    val id: Int = 0
    val month: Int = 0
    val day: Int = 0
    val year: Int = 0

    val payment: Double = 0.0

    val actualString: String = ""
    val subject: String = ""

    fun createBudget(horsepowers: Int): Car {
        val car = Car(horsepowers)
        cars.add(car)
        return car
    }
}