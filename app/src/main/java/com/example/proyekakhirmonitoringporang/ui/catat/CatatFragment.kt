package com.example.proyekakhirmonitoringporang.ui.catat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyekakhirmonitoringporang.api.getLahan.GetLahan
import com.example.proyekakhirmonitoringporang.api.inputPanen.InputPanenRes
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.example.proyekakhirmonitoringporang.databinding.FragmentCatatBinding
import kotlinx.android.synthetic.main.activity_lahan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatatFragment : Fragment() {

    private lateinit var dashboardViewModel: CatatViewModel
    private var _binding: FragmentCatatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(CatatViewModel::class.java)

        _binding = FragmentCatatBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textCatat
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        generalButton()



        return root
    }

    private fun generalButton() {
        binding.btnSimpanPanen.setOnClickListener {
            catatPanen()
        }
    }


    private fun catatPanen() {
        RetrofitClient.getInstance.inputPanen(
            binding.inputIdLahan.text.toString(),
            binding.editInptUmbi.text.toString(),
            binding.editInptKatak.text.toString(),
            binding.editTanggalPanen.text.toString()
        ).enqueue(object : Callback<InputPanenRes> {

            override fun onFailure(call: Call<InputPanenRes>, t: Throwable) {
                Toast.makeText(this@CatatFragment.requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<InputPanenRes>, response: Response<InputPanenRes>) {
                val res = response.body()!!
                if (res.success ) {
                    Toast.makeText(this@CatatFragment.requireContext(), res.message, Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this@CatatFragment.requireContext(), res.success.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}