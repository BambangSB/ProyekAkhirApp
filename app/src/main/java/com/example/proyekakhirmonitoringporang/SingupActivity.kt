package com.example.proyekakhirmonitoringporang

import android.content.Intent
import android.os.Bundle
import android.view.View
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
        if (dft_namalengkap.text!!.isEmpty()) {
            dft_namalengkap.error = "Nama tidak boleh kosong"
            dft_namalengkap.requestFocus() //crusor langsung kesini jika error
            return //jika nama kosong, tidak eksekusi code selanjutnya
        } else if (dft_email.text!!.isEmpty()) {
            dft_email.error = "Email tidak boleh kosong"
            dft_email.requestFocus()
            return
        } else if (dft_password.text!!.isEmpty()) {
            dft_password.error = "Password tidak boleh kosong"
            dft_password.requestFocus()
            return
        } else if (dft_kelompok.text!!.isEmpty()) {
            dft_kelompok.error = "Pilih kelompok yang ada"
            dft_kelompok.requestFocus()
            return
        }

        pb_signUp.visibility = View.VISIBLE
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
                pb_signUp.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success == 1){
                    Toast.makeText(this@SingupActivity, respon.message, Toast.LENGTH_SHORT)
                        .show()
                } else{
                    Toast.makeText(this@SingupActivity, respon.message, Toast.LENGTH_SHORT)
                        .show()
                }

            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                pb_signUp.visibility = View.GONE
                Toast.makeText(
                    this@SingupActivity, "Error: " + t.message, Toast.LENGTH_SHORT
                ).show()
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