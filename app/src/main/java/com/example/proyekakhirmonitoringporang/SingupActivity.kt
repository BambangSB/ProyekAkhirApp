package com.example.proyekakhirmonitoringporang

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyekakhirmonitoringporang.api.daftar.RegisterResponse
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import kotlinx.android.synthetic.main.activity_singup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingupActivity : AppCompatActivity() {

//    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        val kelompok = resources.getStringArray(R.array.array_kelompok)
//        val arrayAdapter = ArrayAdapter(R.id.kelompok_item,kelompok)
//    }
//
//    private fun ArrayAdapter(kelompokItem: Int, kelompok: Array<String>): Any {
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        dft_daftar.setOnClickListener {
//            Toast.makeText(
//                applicationContext,
//                "Selamat Berhasil Mendaftar",
//                Toast.LENGTH_LONG
//            ).show()
            getData()
        }



        dft_masuk.setOnClickListener {
            moveLogin()
        }

//        dft_email.doOnTextChanged { text, start, before, count ->
//            if (text!!.)
//        }
    }

    private fun getData() {
        RetrofitClient.getInstance.register(
            dft_namalengkap.text.toString(),
            dft_email.text.toString(),
            dft_password.text.toString(),
            dft_telepon.text.toString(),
            dft_alamat.text.toString(),
            dft_kelompok.text.toString(),
            dft_foto.text.toString()

        ).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()
                    Toast.makeText(
                        this@SingupActivity,
                        response.message(),
//                        "Berhasil mendaftar, Tunggu admin untuk menerima"
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    Toast.makeText(
                        this@SingupActivity,
                        response.message(),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {

                Toast.makeText(
                    this@SingupActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        })
    }

    private fun moveWellcome() {
        startActivity(Intent(this, WellcomeScreen::class.java))
        finish()
    }

    private fun moveLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


}