package com.example.proyekakhirmonitoringporang

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.proyekakhirmonitoringporang.adapter.AdapterLahan
import com.example.proyekakhirmonitoringporang.api.getLahan.GetLahan
import com.example.proyekakhirmonitoringporang.api.getLahan.Massage
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import com.example.proyekakhirmonitoringporang.lahan.TambahLahan
import kotlinx.android.synthetic.main.activity_lahan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LahanActivity : AppCompatActivity() {

    lateinit var adapter: AdapterLahan
    lateinit var rvTampilLahan: RecyclerView
    lateinit var swipeContainer: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lahan)

        swipeContainer = findViewById(R.id.swipeContainer)


        rvTampilLahan = findViewById(R.id.rv_tambahLahan)

        refresh()

//        getLahan()

        button()
    }

    private fun refresh() {
        swipeContainer.setOnRefreshListener {
            // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.
            getLahan(0)
        }

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);
    }



    private fun button() {
        fb_tambahLahan.setOnClickListener {
            startActivity(Intent(this, TambahLahan::class.java))
        }
    }

    fun displayLahan() {

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvTampilLahan.adapter = AdapterLahan(listLahan)
        rvTampilLahan.layoutManager = layoutManager

    }

    private var listLahan: ArrayList<Massage> = ArrayList()
    fun getLahan(page: Int) {
        pb_lahan.visibility = View.VISIBLE
        val id = SharedPref(this).getUser()!!.id
        RetrofitClient.getInstance.getLahan(id).enqueue(object : Callback<GetLahan> {
            override fun onFailure(call: Call<GetLahan>, t: Throwable) {
                pb_lahan.visibility = View.GONE
                Toast.makeText(this@LahanActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<GetLahan>, response: Response<GetLahan>) {
                val res = response.body()!!
                if (res.massage.isEmpty()) {
                    pb_lahan.visibility = View.GONE
                    tv_statusLahan.visibility = View.VISIBLE
                    swipeContainer.setRefreshing(false)

                }else{
                    pb_lahan.visibility = View.GONE
                    swipeContainer.setRefreshing(false)
                    tv_statusLahan.visibility = View.GONE
//                    val arrayLahan = ArrayList<GetLahan>()
                    listLahan = res.massage
                    displayLahan()
                    Log.d("hasil", res.massage.toString())

                }
            }
        })
    }


    override fun onResume() {
        super.onResume()
        getLahan(0)
    }


}