package com.example.proyekakhirmonitoringporang.api.inputPanen


import com.google.gson.annotations.SerializedName

data class InputPanenRes(
    @SerializedName("message")
    val message: String,
    @SerializedName("panen")
    val panen: Panen,
    @SerializedName("success")
    val success: Boolean
)