package com.hanium

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {
    @GET("/foods/ranking")
    fun getFoodRanking(): Call<RetrofitResponse>

    @GET("/foods/chicken")
    fun getChickenStores(): Call<RetrofitResponse>

    @GET("/foods/pizza")
    fun getPizzaStores(): Call<RetrofitResponse>

    @GET("/foods/hamburger")
    fun getHamburgerStores(): Call<RetrofitResponse>

    @GET("/foods/chinese")
    fun getChineseStores(): Call<RetrofitResponse>

    @GET("/foods/western")
    fun getWesternStores(): Call<RetrofitResponse>

    @GET("/foods/school")
    fun getSchoolStores(): Call<RetrofitResponse>

    @GET("/foods/other")
    fun getOtherStores(): Call<RetrofitResponse>

    @POST("/user/login")
    fun login(@Body jsonparams: LoginModel) : Call<LoginResult>

    @POST("/user/signup")
    @Headers("accept: application/json", "content-type: application/json")
    fun signUp(@Body jsonparams: SignUpModel) : Call<SignUpResult>
}