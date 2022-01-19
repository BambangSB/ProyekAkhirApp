package com.example.proyekakhirmonitoringporang.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Base64
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.example.proyekakhirmonitoringporang.api.Petani
import com.example.proyekakhirmonitoringporang.api.getLahan.Massage
import com.google.gson.Gson
import java.io.ByteArrayOutputStream

class SharedPref(activity: Activity) {

    val login = "login"
    val nama = "nama"
    val kelompok_id = "kelompok_id"
    val email = "email"
    val alamat = "alamat"
    val telepon = "telepon"

    val user = "user"

    val lahan = "lahan"
    val idLahan = "id_lahan"


    val mypref = "MAIN_PREF"
    val sp: SharedPreferences //sebagai shared preference


    init {
        //tambahkan value sp nya
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun saveImage(imageView: ImageView, context: Context) {
        val baos = ByteArrayOutputStream()
        val bitmap = imageView.drawable.toBitmap()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val encodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)

        with(sp.edit()) {
            putString("encodedImage", encodedImage)
            apply()
        }
    }

    fun setStatusLogin(status: Boolean) {
        sp.edit()
            .putBoolean(login, status)
            .apply()
    }

    fun getStatusLogin(): Boolean {
        return sp.getBoolean(login, false)
    }

    fun setUser(value: Petani) {
        val data: String = Gson().toJson(value, Petani::class.java)
        sp.edit().putString(user, data).apply()
    }

    fun getUser(): Petani? {
        val data: String = sp.getString(user, null) ?: return null
        return Gson().fromJson<Petani>(data, Petani::class.java)
    }

    fun setString(key: String, vlaue: String) {
        sp.edit().putString(key, vlaue).apply()
    }

    fun getString(key: String): String {
        return sp.getString(key, "")!!
    }

    fun setLahan(value: Massage) {
        val dataLahan: String = Gson().toJson(value, Massage::class.java)
        sp.edit().putString(lahan, dataLahan).apply()
    }

    fun getLahan(): Massage? {
        val dataLahan: String = sp.getString(lahan, null) ?: return null
        return Gson().fromJson<Massage>(dataLahan, Massage::class.java)
    }

    fun setIdLahan(key: String, value: String) {
        sp.edit().putString(key, value.toString()).apply()
    }

    fun getIdLahan(key: String): String {
        return sp.getString(key, "")!!
    }

    fun clear() {
        sp.edit().clear().apply()
    }


}
