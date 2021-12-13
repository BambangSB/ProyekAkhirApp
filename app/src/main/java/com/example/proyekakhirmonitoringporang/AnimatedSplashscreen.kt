package com.example.proyekakhirmonitoringporang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_animated_splashscreen.*

class AnimatedSplashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_splashscreen)

        val animation_top = AnimationUtils.loadAnimation(this, R.anim.splash_anim_top)
        val animation_bot = AnimationUtils.loadAnimation(this, R.anim.splash_anim_bot)

        splashImage.startAnimation(animation_top)
        splashNama.startAnimation(animation_bot)

        val animationTimeout = 4000
        val homeIntent = Intent(this, WellcomeScreen::class.java)

        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        },animationTimeout.toLong())
    }
}