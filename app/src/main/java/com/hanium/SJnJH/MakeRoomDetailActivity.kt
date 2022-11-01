package com.hanium.SJnJH

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hanium.databinding.ActivityMakeRoomDetailBinding

class MakeRoomDetailActivity : AppCompatActivity() {
    val binding by lazy{ ActivityMakeRoomDetailBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}