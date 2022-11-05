package com.hanium.SJnJH

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.hanium.Chat.PostActivity
import com.hanium.R


class MatchingReadyActivity : AppCompatActivity() {
    lateinit var cancerBt : Button
    lateinit var nowNum : TextView
    lateinit var matchNum : TextView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_ready)

        cancerBt = findViewById(R.id.cancerBt)
        nowNum = findViewById(R.id.nowNum)
        matchNum = findViewById(R.id.matchNum)

        val totalPrice=Intent().getStringExtra("totalPrice")
        val match_num=Intent().getStringExtra("matchNum")
        Log.d("asd", match_num.toString())
        nowNum.text= "1"
        matchNum.text=match_num.toString()

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