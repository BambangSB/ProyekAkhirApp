package com.example.proyekakhirmonitoringporang.api


import com.google.gson.annotations.SerializedName

data class Petani(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kelompok_id")
    val kelompokId: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("telepon")
    val telepon: String,
    @SerializedName("updated_at")
    val updatedAt: String
)