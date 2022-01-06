package com.example.proyekakhirmonitoringporang.lahan

//import com.github.drjacky.imagepicker.ImagePicker
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.proyekakhirmonitoringporang.LahanActivity
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.api.inputLahan.InputLahan
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import com.github.dhaval2404.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.toMultipartBody
import kotlinx.android.synthetic.main.activity_singup.*
import kotlinx.android.synthetic.main.activity_tambah_lahan.*
import kotlinx.android.synthetic.main.fragment_profil.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class TambahLahan : AppCompatActivity() {

    private lateinit var s: SharedPref
    private var mProfileUri: File? = null

    lateinit var idPetani: TextView
    lateinit var idKelompok: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_lahan)

        s = SharedPref(this)

        init()
        setData()
        generalButton()

    }

    private fun init() {
        idPetani = findViewById<EditText>(R.id.il_idPetani)
        idKelompok = findViewById<EditText>(R.id.il_idKelompok)
    }

    fun setData(){

        if (s.getUser() == null){
            return
        }

        val user = s.getUser()!!

        idPetani.text = user.id.toString()
        idKelompok.text = user.kelompokId

    }

    fun generalButton() {

        btn_tambahFotoLahan.setOnClickListener {

            imagePicker()
        }

        il_btn_tambahLahan.setOnClickListener {
            pb_tambahLahan.visibility = View.VISIBLE
            if (mProfileUri == null) {
                pb_tambahLahan.visibility = View.GONE
                Toast.makeText(this, "Masukkan Foto Terlebih Dahulu", Toast.LENGTH_SHORT).show()
            } else {
                pb_tambahLahan.visibility = View.GONE
                uploadFoto(mProfileUri!!)
                startActivity(Intent(this, LahanActivity::class.java))
                finish()
//                Log.d("tag :", waktu.hour.toString())
            }

        }

    }

    private fun imagePicker() {
        ImagePicker.with(this)
            .crop()
            .compress(1024) //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                512,
                512
            )  //Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                mProfileUri = File(fileUri.path!!)
                img_tabmahLahan.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    private fun uploadFoto(file: File) {

        if (il_idPetani.text!!.isEmpty()) {
            il_idPetani.error = "Nama tidak boleh kosong"
            il_idPetani.requestFocus() //crusor langsung kesini jika error
            return //jika nama kosong, tidak eksekusi code selanjutnya
        } else if (il_idKelompok.text!!.isEmpty()) {
            il_idKelompok.error = "Email tidak boleh kosong"
            il_idKelompok.requestFocus()
            return
        } else if (il_namaLahan.text!!.isEmpty()) {
            il_namaLahan.error = "Password tidak boleh kosong"
            il_namaLahan.requestFocus()
            return
        } else if (il_alamatLahan.text!!.isEmpty()) {
            il_alamatLahan.error = "Pilih kelompok yang ada"
            il_alamatLahan.requestFocus()
            return
        } else if (il_luasLahan.text!!.isEmpty()) {
            il_luasLahan.error = "Pilih kelompok yang ada"
            il_luasLahan.requestFocus()
            return
        }

        pb_tambahLahan.visibility = View.VISIBLE

        val fileImage = file.toMultipartBody("foto")
        RetrofitClient.getInstance.inputLahan(
            il_idPetani.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            il_idKelompok.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            il_namaLahan.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            il_alamatLahan.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            il_luasLahan.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),

            fileImage!!
        )
            .enqueue(object : Callback<InputLahan> {

                override fun onResponse(call: Call<InputLahan>, response: Response<InputLahan>) {
                    if (response.isSuccessful) {
                        val respon = response.body()!!
                        if (respon.success == true) {
                            pb_tambahLahan.visibility = View.GONE
                            Toast.makeText(this@TambahLahan, respon.message, Toast.LENGTH_LONG)
                                .show()
                        } else if (respon.success == false) {
                            pb_tambahLahan.visibility = View.GONE
                            Toast.makeText(this@TambahLahan, respon.message, Toast.LENGTH_LONG)
                                .show()
                        } else {
                            pb_tambahLahan.visibility = View.GONE
                            Toast.makeText(this@TambahLahan, respon.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

                override fun onFailure(call: Call<InputLahan>, t: Throwable) {
                    pb_tambahLahan.visibility = View.GONE
                    Toast.makeText(
                        this@TambahLahan, "Error: " + t.message, Toast.LENGTH_LONG
                    ).show()
                }

            })

    }

}