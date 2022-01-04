package com.example.proyekakhirmonitoringporang.api.inputLahan


import com.google.gson.annotations.SerializedName

data class InputLahan(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("tanam")
    val tanam: Tanam
)