package com.hanium.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.hanium.R

class ClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)
        val backBtn: ImageButton = findViewById(R.id.class_back_btn)
        val confirmBtn: Button = findViewById(R.id.class_confirm_btn)

        confirmBtn.setOnClickListener{
            val intent = Intent(this, ClassItemActivity::class.java)
            startActivity(intent)
            finish()
        }
        backBtn.setOnClickListener{
            finish()
        }
    }
}