package com.example.proyekakhirmonitoringporang.api.inputPanen


import com.google.gson.annotations.SerializedName

data class Panen(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lahan_id")
    val lahanId: String,
    @SerializedName("panen_katak")
    val panenKatak: String,
    @SerializedName("panen_umbi")
    val panenUmbi: String,
    @SerializedName("tanggal")
    val tanggal: String,
    @SerializedName("updated_at")
    val updatedAt: String
)