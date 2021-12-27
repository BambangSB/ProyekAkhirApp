package com.example.proyekakhirmonitoringporang.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.proyekakhirmonitoringporang.app.UserModel
import com.google.gson.Gson

class SharedPref(activity: Activity) {

    val login = "login"
    val nama = "nama"
    val kelompok_id = "kelompok_id"
    val email = "email"
    val alamat = "alamat"
    val telepon = "telepon"


    val mypref = "MAIN_PREF"
    val sp: SharedPreferences //sebagai shared preference

    init {
        //tambahkan value sp nya
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean) {
        sp.edit()
            .putBoolean(login, status)
            .apply()
    }

    fun getStatusLogin():Boolean{
        return sp.getBoolean(login,false)
    }

//    fun getUser(): UserModel? {
//        val data:String = sp.getString(user, null) ?: return null
//        return Gson().fromJson<UserModel>(data, UserModel::class.java)
//    }

    fun setString(key: String, vlaue: String) {
        sp.edit().putString(key, vlaue).apply()
    }

    fun getString(key: String): String {
        return sp.getString(key, "")!!
    }

    fun clear(){
        sp.edit().clear().apply()
    }


}