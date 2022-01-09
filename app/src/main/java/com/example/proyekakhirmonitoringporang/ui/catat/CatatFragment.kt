package com.example.proyekakhirmonitoringporang.ui.catat

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyekakhirmonitoringporang.api.inputPanen.InputPanenRes
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.example.proyekakhirmonitoringporang.databinding.FragmentCatatBinding
import kotlinx.android.synthetic.main.activity_lahan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CatatFragment : Fragment() {

    var cal: Calendar = Calendar.getInstance()
    private var tvTanggal: TextView? = null


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

        tvTanggal = binding.editTanggalPanen


        generalButton()
//        getCalendar()


        return root
    }

    private fun getCalendar() {
        DatePickerDialog(
            this.requireContext(),
            dateSetListener,
            // set DatePickerDialog to point to today's date when it loads up
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.ROOT)
        tvTanggal!!.text = sdf.format(cal.time)
    }

    private fun generalButton() {
        binding.btnSimpanPanen.setOnClickListener {
            catatPanen()
        }

        binding.editTanggalPanen.setOnClickListener {
            getCalendar()
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
                Toast.makeText(this@CatatFragment.requireContext(), t.message, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<InputPanenRes>, response: Response<InputPanenRes>) {
                val res = response.body()!!
                if (res.success) {
                    Toast.makeText(
                        this@CatatFragment.requireContext(),
                        res.message,
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    Toast.makeText(
                        this@CatatFragment.requireContext(),
                        res.success.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}