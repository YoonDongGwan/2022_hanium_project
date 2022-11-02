package com.hanium

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @GET("/foods/ranking")
    fun getFoodRanking(): Call<RetrofitResponse>

}