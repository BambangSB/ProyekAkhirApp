package com.example.proyekakhirmonitoringporang.api.sensor


import com.google.gson.annotations.SerializedName

data class Massage(
    @SerializedName("info_kelembapan")
    val infoKelembapan: String,
    @SerializedName("info_ph")
    val infoPh: String,
    @SerializedName("kelembapan")
    val kelembapan: Int,
    @SerializedName("lahan_id")
    val lahanId: Int,
    @SerializedName("ph")
    val ph: Int,
    @SerializedName("rekom_kelembapan")
    val rekomKelembapan: String,
    @SerializedName("rekom_ph")
    val rekomPh: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("tanggal")
    val tanggal: String

)