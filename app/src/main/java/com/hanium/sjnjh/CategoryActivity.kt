package com.hanium.sjnjh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.hanium.R


class CategoryActivity : AppCompatActivity(){
    lateinit var btBack : ImageButton
    lateinit var chickenBt : ImageButton
    lateinit var pizzaBt : ImageButton
    lateinit var hamBt : ImageButton
    lateinit var chinaBt : ImageButton
    lateinit var koreaBt : ImageButton
    lateinit var westBt : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        btBack = findViewById(R.id.btBack)
        chickenBt = findViewById(R.id.chickenBt)
        pizzaBt = findViewById(R.id.pizzaBt)
        hamBt = findViewById(R.id.hamBt)
        chinaBt = findViewById(R.id.chinaBt)
        koreaBt = findViewById(R.id.koreaBt)
        westBt = findViewById(R.id.westBt)


       btBack.setOnClickListener(){
            onBackPressed()
        }

        chickenBt.setOnClickListener(){
            val nextIntent = Intent(this, DeliveryStoresActivity::class.java)
            startActivity(nextIntent)
        }

        pizzaBt.setOnClickListener(){
            val nextIntent = Intent(this, DeliveryStoresActivity::class.java)
            startActivity(nextIntent)
        }

        hamBt.setOnClickListener(){
            val nextIntent = Intent(this, DeliveryStoresActivity::class.java)
            startActivity(nextIntent)
        }

        chinaBt.setOnClickListener(){
            val nextIntent = Intent(this, DeliveryStoresActivity::class.java)
            startActivity(nextIntent)
        }

        koreaBt.setOnClickListener(){
            val nextIntent = Intent(this, DeliveryStoresActivity::class.java)
            startActivity(nextIntent)
        }

        westBt.setOnClickListener(){
            val nextIntent = Intent(this, DeliveryStoresActivity::class.java)
            startActivity(nextIntent)
        }



    }



}