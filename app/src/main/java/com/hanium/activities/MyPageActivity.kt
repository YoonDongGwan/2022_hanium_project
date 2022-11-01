package com.hanium.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.hanium.R

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        val purListBtn: Button = findViewById(R.id.mypage_purList_btn)
        val processPurBtn: Button = findViewById(R.id.mypage_progressPur_btn)

        purListBtn.setOnClickListener(onClickListener)
        processPurBtn.setOnClickListener(onClickListener)

    }
    private val onClickListener = View.OnClickListener { view ->
        when(view.id){
            R.id.mypage_purList_btn -> {
                var intent = Intent(this, HistoryActivity::class.java)
                startActivity(intent)
            }
            R.id.mypage_progressPur_btn -> {
                var intent = Intent(this, ProgressPurActivity::class.java)
                startActivity(intent)
            }
        }
    }

}