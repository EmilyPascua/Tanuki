package com.example.tanuki

import com.google.firebase.database.DataSnapshot

class Users  {
    var name: String? = null
    var uid: String? = null
    var idToken: String? = null
    var weeklyBudget: Float? = null
    var monthlyBudget: Float? = null

    constructor(){}
    constructor(name: String, uid: String, idToken: String, weeklyBudget: Float, monthlyBudget: Float) {
        this.name = name
        this.uid = uid
        this.idToken = idToken
        this.weeklyBudget = weeklyBudget
        this.monthlyBudget = monthlyBudget
    }
}

