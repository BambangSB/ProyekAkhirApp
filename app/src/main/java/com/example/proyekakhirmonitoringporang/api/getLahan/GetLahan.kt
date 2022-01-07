package com.example.proyekakhirmonitoringporang.api.getLahan


import com.google.gson.annotations.SerializedName

data class GetLahan(
    @SerializedName("massage")
    val massage: ArrayList<Massage> = ArrayList(),
    @SerializedName("success")
    val success: Int
)