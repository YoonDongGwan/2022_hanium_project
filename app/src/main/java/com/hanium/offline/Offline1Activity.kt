package com.hanium.offline

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.hanium.R

class Offline1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline1)
        val numberOfPerson: EditText = findViewById(R.id.offline1_edittext)
        val nextBtn: Button = findViewById(R.id.offline1_nextBtn)

        numberOfPerson.addTextChangedListener {
            if (numberOfPerson.text.isNotEmpty()){
                nextBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.mainColor))
                nextBtn.setTextColor(Color.WHITE)
            }else{
                nextBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.appGrey))
                nextBtn.setTextColor(Color.BLACK)
            }
        }
        nextBtn.setOnClickListener {
            val intent: Intent = Intent(this, Offline2Activity::class.java)
            startActivity(intent)
        }

    }
}