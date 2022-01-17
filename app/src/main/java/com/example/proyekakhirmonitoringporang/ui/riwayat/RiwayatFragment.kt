package com.example.proyekakhirmonitoringporang.ui.riwayat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.adapter.AdapterRiwayat
import com.example.proyekakhirmonitoringporang.api.sensor.Massage
import com.example.proyekakhirmonitoringporang.api.sensor.SensorRespon
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import kotlinx.android.synthetic.main.fragment_riwayat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var rvRiwayat: RecyclerView
    lateinit var riwayatSwipeRefreshLayout: SwipeRefreshLayout
    private var listRiwayat: ArrayList<Massage> = ArrayList()
    lateinit var pbRiwayat: ProgressBar
    lateinit var tvRiwayatKosong: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_riwayat, container, false)

        rvRiwayat = view.findViewById(R.id.rv_riwayat)
        pbRiwayat = view.findViewById(R.id.pb_riwayat)
        tvRiwayatKosong = view.findViewById(R.id.tv_riwayat_kosong)




        getLahan()



        riwayatSwipeRefreshLayout = view.findViewById(R.id.swp_riwayat) as SwipeRefreshLayout
        riwayatSwipeRefreshLayout.setOnRefreshListener(this)
        riwayatSwipeRefreshLayout.setColorScheme(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        return view
    }

    private fun displayRiwayat() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvRiwayat.adapter = AdapterRiwayat(listRiwayat)
        rvRiwayat.layoutManager = layoutManager
    }

    override fun onRefresh() {
        riwayatSwipeRefreshLayout.isRefreshing = false
        pbRiwayat.visibility = View.GONE
        getLahan()
    }


    private fun getLahan() {
        pbRiwayat.visibility = View.VISIBLE
//        val id = SharedPref(requireActivity()).getUser()!!.id
        RetrofitClient.getInstance.getSensor().enqueue(object : Callback<SensorRespon> {

            override fun onFailure(call: Call<SensorRespon>, t: Throwable) {
                pbRiwayat.visibility = View.GONE
                tvRiwayatKosong.visibility = View.VISIBLE
                Toast.makeText(this@RiwayatFragment.requireContext(), t.message, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<SensorRespon>, response: Response<SensorRespon>) {
                val res = response.body()!!
                if (res.massage.isEmpty()) {
                    pbRiwayat.visibility = View.GONE
                    tvRiwayatKosong.visibility = View.VISIBLE
                    Toast.makeText(
                        this@RiwayatFragment.requireContext(),
                        "Riwayat Kosong",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    pbRiwayat.visibility = View.GONE
                    tvRiwayatKosong.visibility = View.GONE
                    listRiwayat = res.massage
                    displayRiwayat()
                    Log.d("hasil", res.massage.toString())

                }
            }
        })
    }


//    val arrRiwayat: ArrayList<RvRiwayat>get(){
//        val arr = ArrayList<RvRiwayat>()
//
//        val r1 = RvRiwayat()
//        r1.tanggal = "09:24:02 27/04/2021"
//        r1.kelembaban = "45"
//        r1.ph = "6.5"
//        r1.gambar = R.drawable.rv_riwayat
//
//        val r2 = RvRiwayat()
//        r2.tanggal = "09:24:02 27/04/2021"
//        r2.kelembaban = "45"
//        r2.ph = "6.5"
//        r2.gambar = R.drawable.rv_riwayat
//
//        val r3 = RvRiwayat()
//        r3.tanggal = "09:24:02 27/04/2021"
//        r3.kelembaban = "45"
//        r3.ph = "6.5"
//        r3.gambar = R.drawable.rv_riwayat
//
//        val r4 = RvRiwayat()
//        r4.tanggal = "09:24:02 27/04/2021"
//        r4.kelembaban = "45"
//        r4.ph = "6.5"
//        r4.gambar = R.drawable.rv_riwayat
//
//        val r5 = RvRiwayat()
//        r5.tanggal = "09:24:02 27/04/2021"
//        r5.kelembaban = "45"
//        r5.ph = "6.5"
//        r5.gambar = R.drawable.rv_riwayat
//
//        arr.add(r1)
//        arr.add(r2)
//        arr.add(r3)
//        arr.add(r4)
//        arr.add(r5)
//        return arr
//    }


}