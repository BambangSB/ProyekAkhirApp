package com.example.proyekakhirmonitoringporang.lahan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.proyekakhirmonitoringporang.LahanActivity
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.WellcomeScreen
import com.github.dhaval2404.imagepicker.ImagePicker
//import com.github.drjacky.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_tambah_lahan.*

class TambahLahan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_lahan)

        generalButton()
    }

    fun generalButton(){
        il_btn_tambahLahan.setOnClickListener {
            startActivity(Intent(this, LahanActivity::class.java))
            finish()
        }

        il_btn_tambahLahan.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .maxResultSize(512, 512)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!
            // Use the uri to load the image
        }
    }
}