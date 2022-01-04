package com.example.proyekakhirmonitoringporang.api.inputLahan


import com.google.gson.annotations.SerializedName

data class Tanam(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("foto")
    val foto: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kelompok_id")
    val kelompokId: String,
    @SerializedName("luas_lahan")
    val luasLahan: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("petani_id")
    val petaniId: String,
    @SerializedName("updated_at")
    val updatedAt: String
)