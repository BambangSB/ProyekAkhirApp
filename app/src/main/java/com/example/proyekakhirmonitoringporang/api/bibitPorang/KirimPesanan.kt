package com.example.proyekakhirmonitoringporang.api.bibitPorang


import com.google.gson.annotations.SerializedName

data class KirimPesanan(
    @SerializedName("message")
    val message: String,
    @SerializedName("pesan")
    val pesan: Pesan,
    @SerializedName("success")
    val success: Boolean
)