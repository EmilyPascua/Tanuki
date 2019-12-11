package com.example.tanuki

import android.app.Application

class App:Application() {
    companion object {
        lateinit var user:String
        const val botUser = "bot"
    }
}