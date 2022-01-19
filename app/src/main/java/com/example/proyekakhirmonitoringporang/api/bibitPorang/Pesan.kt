package com.example.proyekakhirmonitoringporang.api.bibitPorang


import com.google.gson.annotations.SerializedName

data class Pesan(
    @SerializedName("bibit_id")
    val bibitId: String,
    @SerializedName("catatan")
    val catatan: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("harga_katak")
    val hargaKatak: Int,
    @SerializedName("harga_umbi")
    val hargaUmbi: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("petani_id")
    val petaniId: String,
    @SerializedName("stok_katak")
    val stokKatak: String,
    @SerializedName("stok_umbi")
    val stokUmbi: String,
    @SerializedName("total_bayar")
    val totalBayar: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)