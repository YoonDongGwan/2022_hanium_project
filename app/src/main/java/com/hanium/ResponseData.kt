package com.hanium

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("minPrice")
    val minPrice: Int,
    @SerializedName("deliveryTip")
    val deliveryTip: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
)
