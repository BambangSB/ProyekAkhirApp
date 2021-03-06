package com.example.proyekakhirmonitoringporang.app

import android.media.Image
import com.example.proyekakhirmonitoringporang.api.LoginResponse
import com.example.proyekakhirmonitoringporang.api.bibitPorang.AmbilPesanan
import com.example.proyekakhirmonitoringporang.api.bibitPorang.KirimPesanan
import com.example.proyekakhirmonitoringporang.api.daftar.RegisterResponse
import com.example.proyekakhirmonitoringporang.api.getLahan.GetLahan
import com.example.proyekakhirmonitoringporang.api.inputLahan.InputLahan
import com.example.proyekakhirmonitoringporang.api.inputLahan.Tanam
import com.example.proyekakhirmonitoringporang.api.inputPanen.InputPanenRes
import com.example.proyekakhirmonitoringporang.api.kelompok.KelompokResponse
import com.example.proyekakhirmonitoringporang.api.sensor.SensorRespon
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

    @GET("GetSensor/6")
    fun getSensor(
//        @Path("id") id: Int
    ):Call<SensorRespon>

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

    @FormUrlEncoded
    @POST("InputPesanan")
    fun inputPesanan(
        @Field("petani_id") petaniId: String,
        @Field("bibit_id") bibitId: String,
        @Field("stok_katak") stokKatak: String,
        @Field("stok_umbi") stokUmbi: String,
    ):Call<KirimPesanan>

    @GET("GetPesanan/{id}")
    fun getPesanan(
        @Path("id") id: Int
    ):Call<AmbilPesanan>

}