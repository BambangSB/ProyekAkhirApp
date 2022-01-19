package com.example.proyekakhirmonitoringporang.api.bibitPorang


import com.google.gson.annotations.SerializedName

data class Massage(
    @SerializedName("catatan")
    val catatan: String,
    @SerializedName("harga_katak")
    val hargaKatak: Int,
    @SerializedName("harga_umbi")
    val hargaUmbi: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("pesan_katak")
    val pesanKatak: Int,
    @SerializedName("pesan_umbi")
    val pesanUmbi: Int,
    @SerializedName("petani_id")
    val petaniId: Int,
    @SerializedName("tanggal")
    val tanggal: String,
    @SerializedName("total_bayar")
    val totalBayar: Int
)