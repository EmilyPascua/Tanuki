package com.example.tanuki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    // The main activity will lead to the following pages:
    // 1. Activity feed
    // 2. Calendar
    // 3. The "chat" - or finances
    // These are all fragments as they will inherit the same navigation bar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
