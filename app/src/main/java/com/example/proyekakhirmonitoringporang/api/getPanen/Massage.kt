package com.example.proyekakhirmonitoringporang.api.getPanen


import com.google.gson.annotations.SerializedName

data class Massage(
    @SerializedName("katak")
    val katak: Int,
    @SerializedName("lahan_id")
    val lahanId: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("tanggal")
    val tanggal: String,
    @SerializedName("umbi")
    val umbi: Int
)