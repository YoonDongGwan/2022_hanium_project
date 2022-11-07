package com.hanium.SJnJH

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.hanium.Chat.PostActivity
import com.hanium.LoginResult
import com.hanium.NowNumResult
import com.hanium.R
import com.hanium.RetrofitService
import com.hanium.activities.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread


class MatchingReadyActivity : AppCompatActivity() {
    lateinit var cancerBt : Button
    lateinit var now_num : TextView
    lateinit var match_num : TextView
    lateinit var textMid : TextView

    val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitService::class.java)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_ready)

        cancerBt = findViewById(R.id.cancerBt)
        match_num = findViewById(R.id.matchNum)
        now_num = findViewById(R.id.now_num)
        textMid = findViewById(R.id.textMid)

        val totalPrice=getIntent().getIntExtra("totalPrice",0)
        val matchNum=getIntent().getIntExtra("matchNum",0)
        val location =getIntent().getStringExtra("deliveryPlace")

        var nowNum=5
        var cnt=0


        thread(start = true) {
            while (nowNum!=matchNum) {
                Thread.sleep(1000)
                runOnUiThread{
                    service.getNowNum(matchNum, location.toString()).enqueue(object : Callback<NowNumResult> {
                        override fun onResponse(call: Call<NowNumResult>, response: Response<NowNumResult>) {
                            nowNum= response.body()?.nowNum!!
                            now_num.text=nowNum.toString()
                            textMid.text="/"
                            match_num.text=matchNum.toString()
                            if (nowNum==matchNum && cnt==0){
                                cnt+=1
                                val intent = Intent(this@MatchingReadyActivity, MatchingActivity::class.java)
                                intent.putExtra("num",matchNum)
                                startActivity(intent)
                                finish()
                            }

                        }

                        override fun onFailure(call: Call<NowNumResult>, t: Throwable) {
                            Log.d("state", "onFailure" + t.message.toString())
                        }

                    })
                }

            }
        }


        cancerBt.setOnClickListener(){
            finish()
        }

    }
}