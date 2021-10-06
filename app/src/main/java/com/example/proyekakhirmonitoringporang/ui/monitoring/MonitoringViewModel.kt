package com.example.proyekakhirmonitoringporang.ui.monitoring

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MonitoringViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ini Halaman Catat"
    }
    val text: LiveData<String> = _text
}