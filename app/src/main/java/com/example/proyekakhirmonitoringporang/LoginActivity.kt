package com.example.proyekakhirmonitoringporang

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyekakhirmonitoringporang.api.LoginResponse
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_singup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var s:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)

        login_btn_masuk.setOnClickListener {
            getDataLogin()
        }

        msk_daftar.setOnClickListener {
            startActivity(Intent(this, SingupActivity::class.java))
        }

    }


    private fun getDataLogin() {

        if (edit_email.text!!.isEmpty()){
            edit_email.error = "Email tidak boleh kosong"
            edit_email.requestFocus()
            return
        }
        else if (edit_password.text!!.isEmpty()) {
            edit_password.error = "Nama tidak boleh kosong"
            edit_password.requestFocus() //crusor langsung kesini jika error
            return //jika nama kosong, tidak eksekusi code selanjutnya
        }


        RetrofitClient.getInstance.login(
            edit_email.text.toString(),
            edit_password.text.toString()
        ).enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
//                pb_signIn.visibility = View.GONE
//                val respon = response.body()!!
//                if (respon.success == 1){
////                    s = SharedPref(this@LoginActivity)
//                    s.setStatusLogin(true)
//                    s.setString(s.nama, respon.user.nama)
//                    s.setString(s.telepon, respon.user.telepon)
//                    s.setString(s.alamat, respon.user.alamat)
//
//                    moveIntent()
//                    finishAffinity()
//                    Toast.makeText(this@LoginActivity, respon.message, Toast.LENGTH_SHORT).show()
//                } else{
//                    pb_signIn.visibility = View.GONE
//                    Toast.makeText(this@LoginActivity, respon.message, Toast.LENGTH_SHORT)
//                        .show()
//                }
//
//            }
//
//            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
//                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
//            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                pb_signIn.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success == 1){
                    pb_signIn.visibility = View.VISIBLE
                    s.setStatusLogin(true)
                    s.setUser(respon.petani)
//                    s.setString(s.nama, respon.petani.nama)
//                    s.setString(s.telepon, respon.petani.telepon)
//                    s.setString(s.alamat, respon.petani.alamat)

                    moveIntent()
                    finishAffinity()
                    Toast.makeText(this@LoginActivity, respon.message, Toast.LENGTH_SHORT).show()
                } else {
                    pb_signIn.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, respon.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                pb_signIn.visibility = View.GONE
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun moveIntent() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}