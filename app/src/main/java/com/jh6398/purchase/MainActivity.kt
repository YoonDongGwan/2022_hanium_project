package com.jh6398.purchase

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var bt1 : Button
    lateinit var bt2 : Button
    lateinit var bt3 : Button
    lateinit var bt4 : Button
    lateinit var bt5 : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt1 = findViewById(R.id.bt1)
        bt2 = findViewById(R.id.bt2)
        bt3 = findViewById(R.id.bt3)
        bt4 = findViewById(R.id.bt4)
        bt5 = findViewById(R.id.bt5)

        bt1.setOnClickListener(){
            val nextIntent = Intent(this, Num5Activity::class.java)
            startActivity(nextIntent)
        }

        bt2.setOnClickListener(){
            val nextIntent = Intent(this, Num7Activity::class.java)
            startActivity(nextIntent)
        }

        bt3.setOnClickListener(){
            val nextIntent = Intent(this, Num8Activity::class.java)
            startActivity(nextIntent)
        }

        bt4.setOnClickListener(){
            val nextIntent = Intent(this, Num9Activity::class.java)
            startActivity(nextIntent)
        }

        bt5.setOnClickListener(){
            val nextIntent = Intent(this, Num10Activity::class.java)
            startActivity(nextIntent)
        }



    }



}