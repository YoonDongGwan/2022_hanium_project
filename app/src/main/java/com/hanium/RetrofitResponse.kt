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
data class OrderListData(var date:String, var store : String,var deliveryFood:String,var price:Int)
data class NowNumResult(var nowNum:Int)
data class PreMatchingModel(var totalPrice:Int,var matchNum:Int,var location:String,var UID:Int,var deliveryTip:Int,var storeName2:String)
data class PreMatchingResult(var message: Boolean, var name: String)
data class CheckProgressListResult(var value:Boolean)
data class MyProgressResult(var store: String, var date: String, var matchNum: Int)
data class ProgressListResult(val data: ArrayList<ProgressListData>)
data class ProgressListData(var name: String, var state: String)


