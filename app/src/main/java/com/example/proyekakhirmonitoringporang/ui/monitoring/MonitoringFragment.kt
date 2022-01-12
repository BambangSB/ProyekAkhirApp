package com.example.proyekakhirmonitoringporang.ui.monitoring

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.api.getLahan.GetLahan
import com.example.proyekakhirmonitoringporang.api.sensor.Massage
import com.example.proyekakhirmonitoringporang.api.sensor.SensorRespon
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.example.proyekakhirmonitoringporang.databinding.FragmentMonitoringBinding
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import kotlinx.android.synthetic.main.activity_lahan.*
import kotlinx.android.synthetic.main.fragment_monitoring.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MonitoringFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var s: SharedPref
    lateinit var tvNamaMonitoring: TextView
    lateinit var nKelembapan: TextView
    lateinit var nPh: TextView

    lateinit var swipeLayout: SwipeRefreshLayout

    private lateinit var homeViewModel: MonitoringViewModel
    private var _binding: FragmentMonitoringBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        s = SharedPref(requireActivity())
        init(view)
        setData()

        pb_monitoring.visibility = View.VISIBLE

        nKelembapan = view.findViewById(R.id.nilaiKelembapan)
        nPh = view.findViewById(R.id.nilaiPh)

        getSensor()

        swipeLayout = view.findViewById(R.id.monitoring_swp) as SwipeRefreshLayout
        swipeLayout.setOnRefreshListener(this)
        swipeLayout.setColorScheme(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )


    }



    fun getSensor() {
        //        val id = SharedPref(this.requireActivity()).getUser()!!.id
        RetrofitClient.getInstance.getSensor().enqueue(object : Callback<SensorRespon> {

            override fun onFailure(call: Call<SensorRespon>, t: Throwable) {
                pb_monitoring.visibility = View.GONE
                Toast.makeText(
                    this@MonitoringFragment.requireContext(),
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<SensorRespon>, response: Response<SensorRespon>) {
                val res = response.body()!!
                if (res.massage.isEmpty()) {
                    pb_monitoring.visibility = View.GONE
                    Toast.makeText(
                        this@MonitoringFragment.requireContext(),
                        "Nilai Sensor Kosong",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    pb_monitoring.visibility = View.GONE
                    nKelembapan.text = response.body()!!.massage.last().kelembapan.toString()
                    nPh.text = response.body()!!.massage.last().ph.toString()
                    Log.d("hasil", res.massage.toString())

                }
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_monitoring, container, false)

    }


    private fun init(view: View) {
        tvNamaMonitoring = view.findViewById(R.id.nama_usermonitoring)
    }

    private fun setData() {
        if (s.getUser() == null) {
            return
        }

        val user = s.getUser()!!
        tvNamaMonitoring.text = user.nama
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRefresh() {
        swipeLayout.isRefreshing = false
        getSensor()
    }
}

