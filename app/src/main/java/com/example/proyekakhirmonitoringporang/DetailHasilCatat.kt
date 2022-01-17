package com.example.proyekakhirmonitoringporang

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class DetailHasilCatat : AppCompatActivity() {

    private lateinit var pbItemCatat: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_hasil_catat)

        pbItemCatat = findViewById(R.id.pb_itemCatat)

        pbItemCatat.visibility = View.GONE



    }
}