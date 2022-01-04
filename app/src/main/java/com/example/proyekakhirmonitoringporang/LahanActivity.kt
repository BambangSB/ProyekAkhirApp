package com.example.proyekakhirmonitoringporang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyekakhirmonitoringporang.lahan.TambahLahan
import kotlinx.android.synthetic.main.activity_lahan.*

class LahanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lahan)



        button()
    }

    private fun button(){
        fb_tambahLahan.setOnClickListener {
            startActivity(Intent(this, TambahLahan::class.java))
        }
    }


}