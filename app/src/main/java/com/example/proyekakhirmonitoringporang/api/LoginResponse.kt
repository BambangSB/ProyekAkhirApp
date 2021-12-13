package com.example.proyekakhirmonitoringporang.api


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("petani")
    val petani: Petani,
    @SerializedName("success")
    val success: Int,
    @SerializedName("token_id")
    val tokenId: String
)