package com.bignerdranch.android.cinemaapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

// загрузочный экран
@SuppressLint("CustomSplashScreen")
class LaunchScreenActivity : AppCompatActivity() {
    private val splashDelay: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)
        Handler().postDelayed({
            val intent = Intent(this@LaunchScreenActivity, SignInScreen::class.java)
            startActivity(intent)
            finish()
        }, splashDelay)
    }
}