package com.example.proyekakhirmonitoringporang.ui.monitoring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.databinding.FragmentMonitoringBinding
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import kotlinx.android.synthetic.main.fragment_monitoring.*

class MonitoringFragment : Fragment() {

    private lateinit var s: SharedPref
    lateinit var tvNamaMonitoring:TextView

    private lateinit var homeViewModel: MonitoringViewModel
    private var _binding: FragmentMonitoringBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        s = SharedPref(requireActivity())
        init(view)
        setData()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//            ViewModelProvider(this).get(MonitoringViewModel::class.java)
//
//        _binding = FragmentMonitoringBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
////        val textView: TextView = binding.textMonitoring
////        homeViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = it
////        })
        return inflater.inflate(R.layout.fragment_monitoring, container, false)


    }



    private fun init(view: View) {
        tvNamaMonitoring = view.findViewById(R.id.nama_usermonitoring)
    }

    private fun setData(){
        nama_usermonitoring.text = s.getString(s.nama)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}