package com.hanium.SJnJH

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import com.hanium.Chat.PostActivity
import com.hanium.R


class MatchingReadyActivity : AppCompatActivity() {
    lateinit var cancerBt : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_ready)

        cancerBt = findViewById(R.id.cancerBt)


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