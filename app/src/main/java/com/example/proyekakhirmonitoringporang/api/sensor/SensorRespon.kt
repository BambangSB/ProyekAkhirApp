package com.example.proyekakhirmonitoringporang.api.sensor


import com.google.gson.annotations.SerializedName

data class SensorRespon(
    @SerializedName("massage")
    val massage: ArrayList<Massage> = ArrayList(),
    @SerializedName("success")
    val success: Int

)