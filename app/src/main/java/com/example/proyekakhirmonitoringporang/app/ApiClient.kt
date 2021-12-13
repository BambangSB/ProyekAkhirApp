package com.example.proyekakhirmonitoringporang.app

import com.example.proyekakhirmonitoringporang.api.LoginResponse
import com.example.proyekakhirmonitoringporang.api.daftar.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiClient {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ):Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("telepon") telepon: String,
        @Field("alamat") alamat: String,
        @Field("kelompok_id") kelompok_id: String,
        @Field("foto") foto: String,

    ):Call<RegisterResponse>
}