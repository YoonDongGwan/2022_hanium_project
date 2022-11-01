package com.hanium.sjnjh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hanium.databinding.ActivityMakeIdBinding

class MakeIdActivity : AppCompatActivity(){
    val binding by lazy{ ActivityMakeIdBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }


}