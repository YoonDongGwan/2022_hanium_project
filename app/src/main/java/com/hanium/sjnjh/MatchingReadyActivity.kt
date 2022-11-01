package com.hanium.sjnjh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

    }
}