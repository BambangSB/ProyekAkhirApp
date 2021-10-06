package com.example.proyekakhirmonitoringporang.ui.riwayat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RiwayatViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ini Halaman Catat"
    }
    val text: LiveData<String> = _text
}