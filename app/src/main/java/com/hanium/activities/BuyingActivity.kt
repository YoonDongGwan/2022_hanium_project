package com.hanium.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R
import com.hanium.adapters.BuyingRecyclerViewAdapter

class BuyingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buying)
        val recyclerview: RecyclerView = findViewById(R.id.buying_recyclerview)
        val payBtn: Button = findViewById(R.id.buying_pay_btn)
        val arrayList: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)

        payBtn.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        recyclerview.adapter = BuyingRecyclerViewAdapter(arrayList)
        recyclerview.layoutManager = LinearLayoutManager(this)
    }
}