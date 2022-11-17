package com.hanium.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.hanium.MypageResponse
import com.hanium.R
import com.hanium.RetrofitService
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
        orderListBtn.setOnClickListener(onClickListener)
        processPurBtn.setOnClickListener(onClickListener)
        val preferences: SharedPreferences = getSharedPreferences("UserInfo", 0)
        val user_uid= preferences.getInt("uid", 0)

        service.getMypage(user_uid).enqueue(object : Callback<MypageResponse> {
            override fun onResponse(call: Call<MypageResponse>, response: Response<MypageResponse>) {
                if (response.isSuccessful){
                    var result: MypageResponse? = response.body()
                    univTv.text=result?.school
                    majorTv.text = result?.major
                    nameTv.text=result?.name
                    id = result?.id.toString()
                    orderListBtn.setOnClickListener(){
                        var intent = Intent(this@MyPageActivity, HistoryActivity::class.java)
                        intent.putExtra("name",result?.name)
                        startActivity(intent)
                    }
                }
            }
            override fun onFailure(call: Call<MypageResponse>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })

    }
    private val onClickListener = View.OnClickListener { view ->
        when(view.id){
            R.id.mypage_progressPur_btn -> {
                var intent = Intent(this, ProgressPurActivity::class.java)
                startActivity(intent)
            }
        }
    }

}