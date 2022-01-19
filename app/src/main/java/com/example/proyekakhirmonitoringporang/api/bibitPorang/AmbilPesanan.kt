package com.example.proyekakhirmonitoringporang.api.bibitPorang


import com.google.gson.annotations.SerializedName

data class AmbilPesanan(
    @SerializedName("massage")
    val massage: List<Massage>,
    @SerializedName("success")
    val success: Int
)