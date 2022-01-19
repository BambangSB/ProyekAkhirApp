package com.example.proyekakhirmonitoringporang.activityprofil

import android.annotation.SuppressLint
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
import android.webkit.WebView
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


class WebPorasiteam : AppCompatActivity(){

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view_porasiteam)

        val myWebView: WebView = findViewById(R.id.wb_porasiteam)
        myWebView.settings.javaScriptEnabled = true
        myWebView.loadUrl("http://porasiteam.com/TA/")

    }


}