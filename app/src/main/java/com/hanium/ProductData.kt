package com.hanium

import com.google.gson.annotations.SerializedName

data class ProductData(
    @SerializedName("name")
    val name: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("productor")
    val productor: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("imgUrl")
    val imgUrl: String,
)
