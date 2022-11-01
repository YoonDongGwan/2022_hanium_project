package com.hanium.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.hanium.R

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val backBtn: ImageButton = findViewById(R.id.product_back_btn)
        val oneBuyBtn: Button = findViewById(R.id.product_one_buying_btn)

        oneBuyBtn.setOnClickListener{
            val intent = Intent(this, BuyingActivity::class.java)
            startActivity(intent)
        }
        backBtn.setOnClickListener{
            finish()
        }
    }
}