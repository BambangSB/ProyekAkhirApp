package com.example.proyekakhirmonitoringporang.activityprofil

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.AttributeSet
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.api.daftar.RegisterResponse
import com.example.proyekakhirmonitoringporang.api.getLahan.GetLahan
import com.example.proyekakhirmonitoringporang.api.kelompok.KelompokResponse
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import com.github.dhaval2404.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.toMultipartBody
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profil_info.*
import kotlinx.android.synthetic.main.activity_singup.*
import kotlinx.android.synthetic.main.fragment_catat.*
import kotlinx.android.synthetic.main.fragment_profil.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class ProfilInfo : AppCompatActivity(){

    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_info)

        s = SharedPref(this)

        setProfil()

    }


    private fun setProfil() {
        if (s.getUser() == null){
            return
        }

        val user = s.getUser()!!


        Picasso.get()
            .load(user.foto)
            .resize(50, 50)
            .centerCrop()
            .placeholder(R.drawable.profil_handler)
            .into(img_profil_info)


        nama_info_profil.text = user.nama
        info_profil_idpetani.text = user.id.toString()
        info_profil_idKelompok.text = user.kelompokId
        email_petani_infoProfil.text = user.email
        nomorTlp_infoProfil.text = user.telepon
        alamt_infoProfil.text = user.alamat

    }

    fun loadLastImage(imageView: ImageView, context: Context) {
        /**
         * Loads the last saved image from SharedPreferences into the
         * MainActivity's ImageView
         */
        val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val encodedImage = sharedPref.getString("encodedImage", "DEFAULT")

        if (encodedImage != "DEFAULT") {
            val imageBytes = Base64.decode(encodedImage, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            imageView.setImageBitmap(decodedImage)
        }
    }

}