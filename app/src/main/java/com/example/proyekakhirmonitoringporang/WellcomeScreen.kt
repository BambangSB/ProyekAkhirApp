package com.example.proyekakhirmonitoringporang

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import kotlinx.android.synthetic.main.activity_wellcome_screen.*

class WellcomeScreen : AppCompatActivity() {

    private var backPressedTime = 0L

    private lateinit var s: SharedPref

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

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        } else {
            Toast.makeText(applicationContext, "Tekan kembali meninggalkan aplikasi", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()

    }


    private fun moveIntent(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}