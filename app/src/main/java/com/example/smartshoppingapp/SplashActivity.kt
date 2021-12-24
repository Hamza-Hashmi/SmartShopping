package com.example.smartshoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.content.Intent




class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)

            // close this activity
            finish()
        }, (2 * 1000).toLong()) // wait for 5 seconds

    }
}