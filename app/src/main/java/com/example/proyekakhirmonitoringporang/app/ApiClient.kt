package com.example.proyekakhirmonitoringporang.app

import android.media.Image
import com.example.proyekakhirmonitoringporang.api.LoginResponse
import com.example.proyekakhirmonitoringporang.api.daftar.RegisterResponse
import com.example.proyekakhirmonitoringporang.api.inputLahan.InputLahan
import com.example.proyekakhirmonitoringporang.api.inputLahan.Tanam
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ):Call<LoginResponse>

//    @FormUrlEncoded
//    @POST("register")
//    fun register(
//        @Field("nama") nama: String,
//        @Field("email") email: String,
//        @Field("password") password: String,
//        @Field("telepon") telepon: String,
//        @Field("alamat") alamat: String,
//        @Field("kelompok_id") kelompok_id: String,
////        @Field("foto") foto: String,
//
//    ):Call<RegisterResponse>

    @Multipart
    @POST("register")
    fun registerFoto(
        @Part("nama") nama: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("telepon") telepon: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("kelompok_id") kelompok_id: RequestBody,
        @Part foto: MultipartBody.Part,

        ):Call<RegisterResponse>

    @FormUrlEncoded
    @POST("InputLahan")
    fun inputLahan(
        @Field("petani_id") petani_id: String,
        @Field("kelompok_id") kelompok_id: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("luas_lahan") luas_lahan: String,
        @Field("foto") foto: String,
    ):Call<InputLahan>

}