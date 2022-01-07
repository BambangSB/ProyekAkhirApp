package com.example.proyekakhirmonitoringporang.ui.profil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyekakhirmonitoringporang.LahanActivity
import com.example.proyekakhirmonitoringporang.R
import com.example.proyekakhirmonitoringporang.WellcomeScreen
import com.example.proyekakhirmonitoringporang.databinding.FragmentProfilBinding
import com.example.proyekakhirmonitoringporang.helper.SharedPref
import kotlinx.android.synthetic.main.fragment_profil.*

class ProfilFragment : Fragment() {

    private lateinit var s: SharedPref
    lateinit var tvNama:TextView
    lateinit var tvTelepon:TextView
    lateinit var tvAlamat:TextView

    private lateinit var homeViewModel: ProfilViewModel
    private var _binding: FragmentProfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
//
        s = SharedPref(requireActivity())



        setData()
        button()

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(ProfilViewModel::class.java)

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)




        return root
    }

    private fun init(view: View) {
        tvNama = view.findViewById(R.id.tv_pr_nama)
        tvTelepon = view.findViewById(R.id.tv_pr_telepon)
        tvAlamat = view.findViewById(R.id.tv_pr_alamat)
    }

    fun setData(){
        if (s.getUser() == null){
            return
        }

        val user = s.getUser()!!

        tv_pr_nama.text = user.nama
        tv_pr_telepon.text = user.telepon
        tv_pr_alamat.text = user.alamat
    }

    fun button() {
        btn_signOut.setOnClickListener {
            s.setStatusLogin(false)
            s.clear()
            val intent = Intent(activity, WellcomeScreen::class.java)
//            startActivity(Intent(requireActivity(), WellcomeScreen::class.java))
            activity?.startActivity(intent)
            activity?.finishAffinity()
        }

        btn_lahan_profil.setOnClickListener {
            val intent = Intent(activity, LahanActivity::class.java)
            activity?.startActivity(intent)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
