package com.hanium

import com.google.gson.annotations.SerializedName

data class ProductData(
    @SerializedName("name")
    val name: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
)
