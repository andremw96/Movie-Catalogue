package com.andreamw96.moviecatalogue.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.andreamw96.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            background_splash.cancelAnimation()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 4000)
    }
}
