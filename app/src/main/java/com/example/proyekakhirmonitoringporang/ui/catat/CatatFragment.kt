package com.example.proyekakhirmonitoringporang.ui.catat

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyekakhirmonitoringporang.api.getLahan.GetLahan
import com.example.proyekakhirmonitoringporang.api.inputPanen.InputPanenRes
import com.example.proyekakhirmonitoringporang.app.RetrofitClient
import com.example.proyekakhirmonitoringporang.databinding.FragmentCatatBinding
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import kotlinx.android.synthetic.main.activity_lahan.*
import kotlinx.android.synthetic.main.activity_singup.*
import kotlinx.android.synthetic.main.fragment_catat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CatatFragment : Fragment(), AdapterView.OnItemSelectedListener {
    var cal: Calendar = Calendar.getInstance()
    private var tvTanggal: TextView? = null

    private var idListLahan = ArrayList<Int>()
    private val listNamaLahan = ArrayList<String>()

    private lateinit var dashboardViewModel: CatatViewModel
    private var _binding: FragmentCatatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var pbCatat: ProgressBar


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

        getLahan()
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
        if (binding.editInptUmbi.text.isNullOrEmpty()) {
            binding.editInptUmbi.error = "Nama tidak boleh kosong"
            binding.editInptUmbi.requestFocus() //crusor langsung kesini jika error
            return //jika nama kosong, tidak eksekusi code selanjutnya
        } else if (binding.editInptKatak.text.isNullOrEmpty()) {
            binding.editInptKatak.error = "Email tidak boleh kosong"
            binding.editInptKatak.requestFocus()
            return
        } else if (binding.editTanggalPanen.text.isNullOrEmpty()) {
            binding.editTanggalPanen.error = "Email tidak boleh kosong"
            binding.editTanggalPanen.requestFocus()
            return
        }

        binding.pbCatat.visibility = View.VISIBLE
        RetrofitClient.getInstance.inputPanen(
            binding.tvIdLahan.text.toString(),
            binding.editInptUmbi.text.toString(),
            binding.editInptKatak.text.toString(),
            binding.editTanggalPanen.text.toString()
        ).enqueue(object : Callback<InputPanenRes> {

            override fun onFailure(call: Call<InputPanenRes>, t: Throwable) {
                pbCatat.visibility = View.GONE
                Toast.makeText(this@CatatFragment.requireContext(), t.message, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<InputPanenRes>, response: Response<InputPanenRes>) {
                val res = response.body()!!
                if (res.success) {
                    pbCatat.visibility = View.GONE
                    Toast.makeText(
                        this@CatatFragment.requireContext(),
                        res.message,
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    pbCatat.visibility = View.GONE
                    Toast.makeText(
                        this@CatatFragment.requireContext(),
                        res.success.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }


    fun getLahan() {
        val id = SharedPref(this.requireActivity()).getUser()!!.id
        RetrofitClient.getInstance.getLahan(id).enqueue(object : Callback<GetLahan> {
            override fun onFailure(call: Call<GetLahan>, t: Throwable) {
                Toast.makeText(this@CatatFragment.requireContext(), t.message, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<GetLahan>, response: Response<GetLahan>) {
                val listResponse = response.body()?.massage

                listResponse?.forEach {
                    idListLahan.add(it.id)
                    listNamaLahan.add(it.nama)
                }

                binding.inputIdLahan.onItemSelectedListener = this@CatatFragment
                val adapter = ArrayAdapter(
                    this@CatatFragment.requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    idListLahan
                )
//                binding.inputIdLahan.adapter = adapter
                binding.inputIdLahan.adapter = adapter

            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        p0?.getItemAtPosition(p2)
        if (p0?.selectedItem == binding.inputIdLahan.selectedItem) {
            binding.tvIdLahan.text = binding.inputIdLahan.selectedItem.toString()
            showNamaLahan(listNamaLahan[p2])
        }
    }

    private fun showNamaLahan(namaLahan: String) {
        Log.d("tes", namaLahan)
        binding.inputNamaLahan.text = namaLahan
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}