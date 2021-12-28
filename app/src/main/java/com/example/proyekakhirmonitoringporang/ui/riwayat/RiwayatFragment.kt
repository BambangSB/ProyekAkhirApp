package com.example.proyekakhirmonitoringporang.ui.riwayat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.adapter.AdapterRiwayat
import com.example.proyekakhirmonitoringporang.databinding.FragmentRiwayatBinding
import com.example.proyekakhirmonitoringporang.model.RvRiwayat

class RiwayatFragment : Fragment() {

    lateinit var rvRiwayat:RecyclerView

//    private lateinit var notificationsViewModel: RiwayatViewModel
//    private var _binding: FragmentRiwayatBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        notificationsViewModel =
//            ViewModelProvider(this).get(RiwayatViewModel::class.java)
//
//        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
////        val textView: TextView = binding.textRiwayat
////        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = it
////        })
//
//        return root
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_riwayat, container, false)

        rvRiwayat = view.findViewById(R.id.rv_riwayat)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvRiwayat.adapter = AdapterRiwayat(arrRiwayat)
        rvRiwayat.layoutManager = layoutManager



        return view
    }
    val arrRiwayat: ArrayList<RvRiwayat>get(){
        val arr = ArrayList<RvRiwayat>()

        val r1 = RvRiwayat()
        r1.tanggal = "09:24:02 27/04/2021"
        r1.kelembaban = "45"
        r1.ph = "6.5"
        r1.gambar = R.drawable.rv_riwayat

        val r2 = RvRiwayat()
        r2.tanggal = "09:24:02 27/04/2021"
        r2.kelembaban = "45"
        r2.ph = "6.5"
        r2.gambar = R.drawable.rv_riwayat

        val r3 = RvRiwayat()
        r3.tanggal = "09:24:02 27/04/2021"
        r3.kelembaban = "45"
        r3.ph = "6.5"
        r3.gambar = R.drawable.rv_riwayat

        val r4 = RvRiwayat()
        r4.tanggal = "09:24:02 27/04/2021"
        r4.kelembaban = "45"
        r4.ph = "6.5"
        r4.gambar = R.drawable.rv_riwayat

        val r5 = RvRiwayat()
        r5.tanggal = "09:24:02 27/04/2021"
        r5.kelembaban = "45"
        r5.ph = "6.5"
        r5.gambar = R.drawable.rv_riwayat

        arr.add(r1)
        arr.add(r2)
        arr.add(r3)
        arr.add(r4)
        arr.add(r5)
        return arr
    }


}