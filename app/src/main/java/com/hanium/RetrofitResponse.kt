package com.hanium

import com.google.gson.annotations.SerializedName

data class RetrofitResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: ArrayList<ResponseData>
)

data class LoginResult(var UID:Int)
data class LoginModel(var id: String, var password: String)
data class SignUpModel(var name: String, var id: String, var password: String, var school: String, var major : String)
data class SignUpResult(var message: Boolean)
data class LocationResponse(val code: Int, val message: String, val data: ArrayList<String>)
data class MypageResponse(var school:String, var major:String,var name:String,val id:String)
data class OrderListResponse(var code:Int, var message : String, val data :ArrayList<OrderListData>)
data class OrderListData(var id:String, var store : String, var date:String, var deliveryFood:String,var price:Int)
data class NowNumResult(var nowNum:Int)

