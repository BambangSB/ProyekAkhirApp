package com.example.proyekakhirmonitoringporang

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_singup.*

class SingupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        dft_daftar.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Selamat Berhasil Mendaftar",
                Toast.LENGTH_LONG
            ).show()
            moveWellcome()
        }

        dft_masuk.setOnClickListener {
            moveLogin()
        }

//        dft_email.doOnTextChanged { text, start, before, count ->
//            if (text!!.)
//        }
    }

    private fun moveWellcome() {
        startActivity(Intent(this, WellcomeScreen::class.java))
        finish()
    }

    private fun moveLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


}