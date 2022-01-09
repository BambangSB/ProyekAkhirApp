package com.example.proyekakhirmonitoringporang.api.kelompok


import com.google.gson.annotations.SerializedName

data class KelompokResponse(
    @SerializedName("massage")
    val massage: List<Massage>,
    @SerializedName("success")
    val success: Int
)