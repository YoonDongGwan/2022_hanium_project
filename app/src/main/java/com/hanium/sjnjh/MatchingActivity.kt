package com.hanium.sjnjh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.hanium.R


class MatchingActivity : AppCompatActivity() {
    lateinit var nextBt : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching)

        nextBt = findViewById(R.id.nextBt)

        nextBt.setOnClickListener(){
            val intent = Intent(this, FinalPriceActivity::class.java)
            startActivity(intent)
        }
    }
}