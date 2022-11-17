package com.hanium.offline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.hanium.Chat.ChatRoomListActivity
import com.hanium.R

class Offline2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline2)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent = Intent(this, ChatRoomListActivity::class.java)
            startActivity(intent)
        }, 5000)
    }
}