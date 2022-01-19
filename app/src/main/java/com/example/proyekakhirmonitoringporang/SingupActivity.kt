package com.example.proyekakhirmonitoringporang

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.proyekakhirmonitoringporang.api.daftar.RegisterResponse
import com.example.proyekakhirmonitoringporang.api.getLahan.GetLahan
import com.example.proyekakhirmonitoringporang.api.kelompok.KelompokResponse
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.github.dhaval2404.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.toMultipartBody
import kotlinx.android.synthetic.main.activity_singup.*
import kotlinx.android.synthetic.main.fragment_catat.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class SingupActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var mProfileUri: File? = null
    lateinit var autoCompleteTextView: AutoCompleteTextView
    lateinit var adapterItems: ArrayAdapter<String>

    private var listIdKelompok = ArrayList<Int>()
    private val listNamaKelompok = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        button()

        getKelompok()

//        itemKelompok()

//        dft_email.doOnTextChanged { text, start, before, count ->
//            if (text!!.)
//        }
    }


//    private fun itemKelompok() {
//        val type = arrayOf("1", "2", "3", "4")
//
//        val adapter = ArrayAdapter(
//            this,
//            R.layout.item_kelompok_dropdown,
//            type
//        )
//
//        val editTextFilledExposedDropdown = findViewById<AutoCompleteTextView>(R.id.dft_kelompok)
//        editTextFilledExposedDropdown.setAdapter(adapter)
//    }

    private fun button() {

        dft_daftar.setOnClickListener {
            pb_signUp.visibility = View.VISIBLE
            if (mProfileUri == null) {
                pb_signUp.visibility = View.GONE
                Toast.makeText(this, "Masukkan Foto Terlebih Dahulu", Toast.LENGTH_SHORT).show()
            } else {
                daftarPetani(mProfileUri!!)
//                Log.d("tag :", waktu.hour.toString())
            }
        }


        dft_btn_uploadFoto.setOnClickListener {
            imagePicker()

        }


        dft_masuk.setOnClickListener {
            moveLogin()
        }
    }

    private fun imagePicker() {
        ImagePicker.with(this)
//        ImagePicker.with(this)
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
                dft_imgFoto.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


    private fun daftarPetani(file: File) {

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

        val fileImage = file.toMultipartBody("foto")
        RetrofitClient.getInstance.registerFoto(
            dft_namalengkap.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            dft_email.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            dft_password.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            dft_telepon.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            dft_alamat.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            dft_kelompok.text.toString().toRequestBody("text/plain".toMediaTypeOrNull()),

            fileImage!!
        )
            .enqueue(object : Callback<RegisterResponse> {

                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        val respon = response.body()!!
                        if (respon.success == 1) {
                            pb_signUp.visibility = View.GONE
                            Toast.makeText(this@SingupActivity, respon.petani.nama + ", Selamat berhasil mendaftar. Tunggu diferivikasi oleh admin", Toast.LENGTH_LONG)
                                .show()
                            startActivity(Intent(this@SingupActivity, WellcomeScreen::class.java))
                        } else if (respon.success == 0) {
                            pb_signUp.visibility = View.GONE
                            Toast.makeText(this@SingupActivity, respon.message, Toast.LENGTH_LONG)
                                .show()
                        } else {
                            pb_signUp.visibility = View.GONE
                            Toast.makeText(this@SingupActivity, respon.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    pb_signUp.visibility = View.GONE
                    Toast.makeText(
                        this@SingupActivity, "Error: " + t.message, Toast.LENGTH_LONG
                    ).show()
                }

            })

    }

    private fun getKelompok() {
        RetrofitClient.getInstance.getKelompok().enqueue(object : Callback<KelompokResponse> {

            override fun onFailure(call: Call<KelompokResponse>, t: Throwable) {
                Toast.makeText(this@SingupActivity, t.message, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(
                call: Call<KelompokResponse>,
                response: Response<KelompokResponse>
            ) {
                val listResponse = response.body()?.massage

                listResponse?.forEach {
                    listIdKelompok.add(it.id)
                    listNamaKelompok.add(it.nama)
                }

                spin_daftar.onItemSelectedListener = this@SingupActivity
                val adapter = ArrayAdapter(
                    this@SingupActivity,
                    android.R.layout.simple_spinner_dropdown_item,
                    listIdKelompok
                )
//                binding.inputIdLahan.adapter = adapter
                spin_daftar.adapter = adapter
            }
        })
    }


    private fun moveLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        p0?.getItemAtPosition(p2)
        if (p0?.selectedItem == spin_daftar.selectedItem) {
            dft_kelompok.text = spin_daftar.selectedItem.toString()
            showNamaKelompok(listNamaKelompok[p2])
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun showNamaKelompok(namaKelompok: String) {
        Log.d("tes", namaKelompok)
        tv_daftarNamaKelTani.text = namaKelompok
    }


}