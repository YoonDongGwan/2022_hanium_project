package com.hanium.sjnjh

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hanium.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {
    val binding by lazy{ ActivityHomeBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button2.setOnClickListener(){
            val nextIntent = Intent(this, CategoryActivity::class.java)
            startActivity(nextIntent)
        }
    }

}