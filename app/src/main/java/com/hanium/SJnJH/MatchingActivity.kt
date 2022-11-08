package com.hanium.SJnJH

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.hanium.R


class MatchingActivity : AppCompatActivity() {
    lateinit var nextBt : Button
    lateinit var numberTv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching)

        nextBt = findViewById(R.id.nextBt)
        numberTv = findViewById(R.id.numberTv)
        val num = getIntent().getIntExtra("num",0)

        numberTv.text="${num}/${num}"
        nextBt.setOnClickListener(){
            val intent = Intent(this, FinalPriceActivity::class.java)
            startActivity(intent)
        }
    }
}