package com.hanium.chat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.hanium.R

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        val startBtn: Button = findViewById(R.id.room_start_chat_btn)
        startBtn.setOnClickListener{
            val intent = Intent(this, ChatRoomActivity::class.java)
            startActivity(intent)
        }
    }
}