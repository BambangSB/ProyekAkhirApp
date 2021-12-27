package com.example.proyekakhirmonitoringporang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import kotlinx.android.synthetic.main.activity_animated_splashscreen.*

class AnimatedSplashscreen : AppCompatActivity() {

    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_splashscreen)

        s = SharedPref(this)

        val animation_top = AnimationUtils.loadAnimation(this, R.anim.splash_anim_top)
        val animation_bot = AnimationUtils.loadAnimation(this, R.anim.splash_anim_bot)

        splashImage.startAnimation(animation_top)
        splashNama.startAnimation(animation_bot)

        val animationTimeout = 4000
        val homeIntent = Intent(this, WellcomeScreen::class.java)

        Handler().postDelayed({
            when {
                s.getStatusLogin() -> {
                    startActivity(Intent(this,MainActivity::class.java))
                    finishAffinity()
                }
                else ->{
                    startActivity(Intent(this, WellcomeScreen::class.java))
                    finishAffinity()
                }
            }

        },animationTimeout.toLong())
    }
}