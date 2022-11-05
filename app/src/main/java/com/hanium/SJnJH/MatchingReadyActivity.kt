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


class MatchingReadyActivity : AppCompatActivity() {
    lateinit var cancerBt : Button
    lateinit var now_num : TextView
    lateinit var match_num : TextView

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

        val totalPrice=getIntent().getIntExtra("totalPrice",0)
        val matchNum=getIntent().getIntExtra("matchNum",0)
        val location =getIntent().getStringExtra("deliveryPlace")

        match_num.text=matchNum.toString()

        service.getNowNum(matchNum, location.toString()).enqueue(object : Callback<NowNumResult> {
            override fun onResponse(call: Call<NowNumResult>, response: Response<NowNumResult>) {
                var nowNum=response.body()?.nowNum ?: return
                now_num.text=nowNum.toString()
//               if (user_uid!=-1){
//                    Log.d("login", "성공 +${user_uid}")
//                    val nextIntent = Intent(this@MatchingReadyActivity, MainActivity::class.java)
//                    nextIntent.putExtra("UID", user_uid)
//                    startActivity(nextIntent)
//                }
//                else{
//                    Toast.makeText(applicationContext, "로그인 실패, 아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
//                }
            }

            override fun onFailure(call: Call<NowNumResult>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })

        cancerBt.setOnClickListener(){
            finish()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent = Intent(this, MatchingActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)

    }
}