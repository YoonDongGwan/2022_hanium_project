package com.hanium.SJnJH

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.hanium.R
import com.hanium.databinding.ActivityMakeRoomBinding

class MakeRoomActivity : AppCompatActivity() {
    lateinit var imageButton : ImageButton
    lateinit var makeRoomBt : Button
    val binding by lazy{ ActivityMakeRoomBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        imageButton = findViewById(R.id.imageButton)
        makeRoomBt = findViewById(R.id.makeRoomBt)

        imageButton.setOnClickListener(){
            finish()
        }

        makeRoomBt.setOnClickListener(){
            val nextIntent = Intent(this, DeliveryStoresActivity::class.java)
            startActivity(nextIntent)
        }

    }
}