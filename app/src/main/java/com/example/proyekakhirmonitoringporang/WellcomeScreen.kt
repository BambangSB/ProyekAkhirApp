package com.example.proyekakhirmonitoringporang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_wellcome_screen.*

class WellcomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcome_screen)


        btn_masuk.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btn_daftar.setOnClickListener {
            startActivity(Intent(this, SingupActivity::class.java))
        }
    }

}