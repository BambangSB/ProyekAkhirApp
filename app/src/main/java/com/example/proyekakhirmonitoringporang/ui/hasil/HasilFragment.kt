package com.example.proyekakhirmonitoringporang.ui.hasil

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.adapter.AdapterHasil
import com.example.proyekakhirmonitoringporang.adapter.AdapterLahan
import com.example.proyekakhirmonitoringporang.api.getLahan.GetLahan
import com.example.proyekakhirmonitoringporang.api.getLahan.Massage
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import kotlinx.android.synthetic.main.activity_lahan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HasilFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var adapterHasil: AdapterHasil
    lateinit var swipeHasil: SwipeRefreshLayout
    lateinit var rvHasil: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_hasil, container, false)


        swipeHasil = view.findViewById(R.id.swipe_hasil)

        rvHasil = view.findViewById(R.id.rv_hasil_catat)

        getLahan()

        swipeHasil = view.findViewById(R.id.swipe_hasil) as SwipeRefreshLayout
        swipeHasil.setOnRefreshListener(this)
        swipeHasil.setColorScheme(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        return view
    }



    fun displayLahan() {

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        val layoutManager2 = LinearLayoutManager(requireContext())
        layoutManager2.orientation = LinearLayoutManager.VERTICAL

        rvHasil.adapter = AdapterLahan(listLahan)
        rvHasil.layoutManager = layoutManager
        rvHasil.layoutManager = layoutManager2

    }

    private var listLahan: ArrayList<Massage> = ArrayList()

    fun getLahan() {
        val id = SharedPref(requireActivity()).getUser()!!.id
        RetrofitClient.getInstance.getLahan(id).enqueue(object : Callback<GetLahan> {
            override fun onFailure(call: Call<GetLahan>, t: Throwable) {
                Toast.makeText(this@HasilFragment.requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<GetLahan>, response: Response<GetLahan>) {
                val res = response.body()!!
                if (res.massage.isEmpty()) {
//                    tv_statusLahan.visibility = View.VISIBLE
                    Toast.makeText(this@HasilFragment.requireContext(), "Lahan Kosong", Toast.LENGTH_SHORT).show()

                } else {
//                    tv_statusLahan.visibility = View.GONE
//                    val arrayLahan = ArrayList<GetLahan>()
                    listLahan = res.massage
                    displayLahan()
                    Log.d("hasil", res.massage.toString())

                }
            }
        })
    }

    override fun onRefresh() {
        swipeHasil.isRefreshing = false
        getLahan()
    }
}