package com.example.proyekakhirmonitoringporang.api.getPanen


import com.google.gson.annotations.SerializedName

data class HasilPanen(
    @SerializedName("massage")
    val massage: List<Massage>,
    @SerializedName("success")
    val success: Int
)