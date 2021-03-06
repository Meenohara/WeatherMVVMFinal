package com.applauded.meena.weathermvvmfinal

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.applauded.meena.weathermvvmfinal.DetailsActivity

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long=3000 // 3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, DetailsActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
