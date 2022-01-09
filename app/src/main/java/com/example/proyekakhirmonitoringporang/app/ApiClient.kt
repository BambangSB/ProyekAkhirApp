package com.example.proyekakhirmonitoringporang.app

import android.media.Image
import com.example.proyekakhirmonitoringporang.api.LoginResponse
import com.example.proyekakhirmonitoringporang.api.daftar.RegisterResponse
import com.example.proyekakhirmonitoringporang.api.getLahan.GetLahan
import com.example.proyekakhirmonitoringporang.api.inputLahan.InputLahan
import com.example.proyekakhirmonitoringporang.api.inputLahan.Tanam
import com.example.proyekakhirmonitoringporang.api.inputPanen.InputPanenRes
import com.example.proyekakhirmonitoringporang.api.kelompok.KelompokResponse
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

    @Multipart
    @POST("InputLahan")
    fun inputLahan(
        @Part("petani_id") petani_id: RequestBody,
        @Part("kelompok_id") kelompok_id: RequestBody,
        @Part("nama") nama: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("luas_lahan") luas_lahan: RequestBody,
        @Part foto: MultipartBody.Part,
    ):Call<InputLahan>

    @GET("GetLahan/{id}")
    fun getLahan(
        @Path("id") id: Int
    ):Call<GetLahan>

    @GET("GetKelompok")
    fun getKelompok(

    ):Call<KelompokResponse>

    @FormUrlEncoded
    @POST("InputPanen")
    fun inputPanen(
        @Field("lahan_id") lahanId: String,
        @Field("panen_katak") panenKatak: String,
        @Field("panen_umbi") panenUmbi: String,
        @Field("tanggal") tanggal: String,
    ):Call<InputPanenRes>


}