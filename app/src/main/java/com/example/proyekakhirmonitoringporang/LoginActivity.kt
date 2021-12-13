package com.example.proyekakhirmonitoringporang

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyekakhirmonitoringporang.api.LoginResponse
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private var backPressedTime = 0L

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        } else {
            Toast.makeText(applicationContext, "Tekan kembali untuk keluar", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn_masuk.setOnClickListener {
            getDataLogin()
        }

        msk_daftar.setOnClickListener {
            startActivity(Intent(this, SingupActivity::class.java))
        }

    }

    private fun getDataLogin() {
        RetrofitClient.getInstance.login(
            edit_email.text.toString(),
            edit_password.text.toString()
        ).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()
                    Toast.makeText(this@LoginActivity, response.message(), Toast.LENGTH_LONG).show()
                    moveIntent()
                } else {
                    Toast.makeText(this@LoginActivity, response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun moveIntent() {
        startActivity(Intent(this, BottomNav::class.java))
        finish()
    }
}