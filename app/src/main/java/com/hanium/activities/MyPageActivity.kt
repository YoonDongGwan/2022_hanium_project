package com.hanium.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hanium.CheckProgressListResult
import com.hanium.MypageResponse
import com.hanium.R
import com.hanium.RetrofitService
import com.hanium.SJnJH.FinalPriceActivity
import com.hanium.databinding.ActivityMypageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPageActivity : AppCompatActivity() {

    val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        val orderListBtn: Button = findViewById(R.id.mypage_orderList_btn)
        val processPurBtn: Button = findViewById(R.id.mypage_progressPur_btn)


        val univTv = findViewById<TextView>(R.id.univTv)
        val majorTv = findViewById<TextView>(R.id.majorTv)
        val nameTv = findViewById<TextView>(R.id.nameTv)
        var id=""
        val preferences: SharedPreferences = getSharedPreferences("UserInfo", 0)
        val user_uid= preferences.getInt("uid", 0)

        service.getMypage(user_uid).enqueue(object : Callback<MypageResponse> {
            override fun onResponse(call: Call<MypageResponse>, response: Response<MypageResponse>) {
                if (response.isSuccessful){
                    var result: MypageResponse? = response.body()
                    univTv.text=result?.school
                    majorTv.text = result?.major
                    nameTv.text=result?.name
                    var name = result!!.name
                    id = result?.id.toString()
                    orderListBtn.setOnClickListener(){

                        service.getCheckOrderList(result!!.name).enqueue(object : Callback<CheckProgressListResult> {
                            override fun onResponse(call: Call<CheckProgressListResult>, response: Response<CheckProgressListResult>) {
                                if (response.isSuccessful){
                                    var result: CheckProgressListResult? = response.body()
                                    var value = result!!.value
                                    if (value){
                                        val intent = Intent(this@MyPageActivity, HistoryActivity::class.java)
                                        intent.putExtra("name",name)
                                        startActivity(intent)
                                    }
                                    else{
                                        Toast.makeText(applicationContext, "주문 내역이 없습니다.", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                            override fun onFailure(call: Call<CheckProgressListResult>, t: Throwable) {
                                Log.d("state", "onFailure" + t.message.toString())
                            }

                        })
                    }

                    processPurBtn.setOnClickListener(){
                        service.getCheck(result!!.name).enqueue(object : Callback<CheckProgressListResult> {
                            override fun onResponse(call: Call<CheckProgressListResult>, response: Response<CheckProgressListResult>) {
                                if (response.isSuccessful){
                                    var result: CheckProgressListResult? = response.body()
                                    var value = result!!.value
                                    if (value){
                                        val intent = Intent(this@MyPageActivity, ProgressPurchaseActivity::class.java)
                                        intent.putExtra("name",name)
                                        startActivity(intent)
                                    }
                                    else{
                                        Toast.makeText(applicationContext, "현재 진행 중인 공동배달이 없습니다.", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                            override fun onFailure(call: Call<CheckProgressListResult>, t: Throwable) {
                                Log.d("state", "onFailure" + t.message.toString())
                            }

                        })

                    }
                }
            }
            override fun onFailure(call: Call<MypageResponse>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })

    }

}