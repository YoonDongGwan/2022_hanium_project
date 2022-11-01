package com.hanium

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("/product/books")
    fun getBooks(): Call<RetrofitResponse>

    @GET("/product/electronics")
    fun getElectronics(): Call<RetrofitResponse>
}