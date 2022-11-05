package com.hanium

import retrofit2.Call
import retrofit2.http.*

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

    @GET("/store/menu")
    fun getStoreMenu(@Query("company") company: String): Call<RetrofitResponse>

    @GET("/delivery/location")
    fun getDeliveryLocation(): Call<LocationResponse>

    @GET("/foods/chicken/find")
    fun findChickenStore(@Query("id") id: Int): Call<RetrofitResponse>

    @POST("/user/login")
    fun login(@Body jsonparams: LoginModel) : Call<LoginResult>

    @POST("/user/signup")
    @Headers("accept: application/json", "content-type: application/json")
    fun signUp(@Body jsonparams: SignUpModel) : Call<SignUpResult>

    @GET("user/mypage")
    fun getMypage(@Query("UID") UID: Int): Call<MypageResponse>

    @GET("user/orderlist")
    fun getOrderList(@Query("id") id: String): Call<OrderListResponse>

    @GET("delivery/nownum")
    fun getNowNum(@Query("matchNum") matchNum : Int,@Query("location") location :String) : Call<NowNumResult>

}