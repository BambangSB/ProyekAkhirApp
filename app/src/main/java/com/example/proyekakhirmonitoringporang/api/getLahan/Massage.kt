package com.example.proyekakhirmonitoringporang.api.getLahan


import com.google.gson.annotations.SerializedName

data class Massage(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("foto")
    val foto: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("luas")
    val luas: String,
    @SerializedName("nama")
    val nama: String
)