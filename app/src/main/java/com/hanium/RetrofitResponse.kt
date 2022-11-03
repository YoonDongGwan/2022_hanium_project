package com.hanium

import com.google.gson.annotations.SerializedName

data class RetrofitResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: ArrayList<ProductData>
)

data class LoginResult(var UID:Int)
data class LoginModel(var id: String, var password: String)
data class SignUpModel(var name: String, var id: String, var password: String, var school: String, var major : String)
data class SignUpResult(var message: Boolean)

