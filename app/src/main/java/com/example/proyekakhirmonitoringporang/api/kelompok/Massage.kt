package com.example.proyekakhirmonitoringporang.api.kelompok


import com.google.gson.annotations.SerializedName

data class Massage(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("created_at")
    val createdAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kecamatan")
    val kecamatan: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("updated_at")
    val updatedAt: Any
)