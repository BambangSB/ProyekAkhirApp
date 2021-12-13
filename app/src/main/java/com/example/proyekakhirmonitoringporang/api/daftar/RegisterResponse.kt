package com.example.proyekakhirmonitoringporang.api.daftar


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("petani")
    val petani: Petani,
    @SerializedName("success")
    val success: Int
)