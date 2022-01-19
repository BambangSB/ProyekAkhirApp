package com.example.proyekakhirmonitoringporang.activityprofil

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.api.bibitPorang.AmbilPesanan
import com.example.proyekakhirmonitoringporang.api.bibitPorang.KirimPesanan
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import kotlinx.android.synthetic.main.activity_bibit_porang.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_singup.*
import kotlinx.android.synthetic.main.fragment_profil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BibitPorang : AppCompatActivity() {

    private lateinit var s: SharedPref
    lateinit var swipeBibit: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bibit_porang)

        s = SharedPref(this)
        swipeBibit = findViewById(R.id.swp_bibitPorang)

        setData()
        generalButton()
        getPesanan(0)
        refresh()

    }

    private fun generalButton() {
        btn_pesanBibit.setOnClickListener {
            kirimPesanan()
            getPesanan(0)
        }
    }


    fun setData() {
        if (s.getUser() == null) {
            return
        }

        val user = s.getUser()!!

        pesanBibit_petaniId.text = user.id.toString()
        pesanBibit_idPetaniHasil.text = user.id.toString()
    }

    private fun kirimPesanan() {

        if (pesanBibit_psnKatak.text!!.isEmpty()) {
            pesanBibit_psnKatak.error = "Bibit tidak boleh kosong"
            pesanBibit_psnKatak.requestFocus() //crusor langsung kesini jika error
            return //jika nama kosong, tidak eksekusi code selanjutnya
        } else if (pesanBibit_psnUmbi.text!!.isEmpty()) {
            pesanBibit_psnUmbi.error = "Umbi tidak boleh kosong"
            pesanBibit_psnUmbi.requestFocus()
            return
        }

        pb_pesanBibit.visibility = View.VISIBLE

        RetrofitClient.getInstance.inputPesanan(
            pesanBibit_petaniId.text.toString(),
            pesanBibit_bibitId.text.toString(),
            pesanBibit_psnKatak.text.toString(),
            pesanBibit_psnUmbi.text.toString()

        )
            .enqueue(object : Callback<KirimPesanan> {

                override fun onResponse(
                    call: Call<KirimPesanan>,
                    response: Response<KirimPesanan>
                ) {
                    if (response.isSuccessful) {
                        val respon = response.body()!!
                        if (respon.success) {
                            pb_pesanBibit.visibility = View.GONE
                            Toast.makeText(
                                this@BibitPorang,
                                respon.message,
                                Toast.LENGTH_LONG
                            ).show()
                            pesanBibit_psnKatak.text?.clear()
                            pesanBibit_psnUmbi.text?.clear()

                        } else {
                            pb_pesanBibit.visibility = View.GONE
                            Toast.makeText(
                                this@BibitPorang,
                                "Periksa kembali pesanan anda",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }

                override fun onFailure(call: Call<KirimPesanan>, t: Throwable) {
                    pb_pesanBibit.visibility = View.GONE
                    Toast.makeText(
                        this@BibitPorang, "Error: " + t.message, Toast.LENGTH_SHORT
                    ).show()
                }

            })

    }

    private fun refresh() {
        swipeBibit.setOnRefreshListener {
            // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.
            getPesanan(0)
        }

        swipeBibit.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        );
    }

    private fun getPesanan(page: Int) {
        pb_pesanBibit.visibility = View.VISIBLE
        val id = SharedPref(this).getUser()!!.id
        RetrofitClient.getInstance.getPesanan(id).enqueue(object : Callback<AmbilPesanan> {

            override fun onFailure(call: Call<AmbilPesanan>, t: Throwable) {
                pb_pesanBibit.visibility = View.GONE
                swipeBibit.isRefreshing = false
                Toast.makeText(this@BibitPorang, t.message, Toast.LENGTH_LONG)
                    .show()

            }

            override fun onResponse(call: Call<AmbilPesanan>, response: Response<AmbilPesanan>) {
                val res = response.body()!!
                if (res.massage.isEmpty()) {
                    pb_pesanBibit.visibility = View.GONE
                    swipeBibit.isRefreshing = false
                    Toast.makeText(
                        this@BibitPorang,
                        "Tidak ada pesanan",
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    swipeBibit.isRefreshing = false
                    pb_pesanBibit.visibility = View.GONE
                    pesanBibit_tanggalPesan.text = res.massage.last().tanggal
                    pesanBibit_hargaKatak.text = res.massage.last().hargaKatak.toString()
                    pesanBibit_hargaUmbi.text = res.massage.last().hargaUmbi.toString()
                    pesanBibit_hasilPesanKatak.text = res.massage.last().pesanKatak.toString()
                    pesanBibit_hasilPesanUmbi.text = res.massage.last().pesanUmbi.toString()
                    pesanBibit_totalBayar.text = res.massage.last().totalBayar.toString()
                    pesanBibit_catatan.text = res.massage.last().catatan

                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getPesanan(0)
    }
}
