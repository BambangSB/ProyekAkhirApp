package com.example.proyekakhirmonitoringporang.ui.hasil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyekakhirmonitoringporang.databinding.FragmentCatatBinding
import com.example.proyekakhirmonitoringporang.databinding.FragmentHasilBinding

class HasilFragment : Fragment() {

    private lateinit var dashboardViewModel: HasilViewModel
    private var _binding: FragmentHasilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(HasilViewModel::class.java)

        _binding = FragmentHasilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHasil
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}