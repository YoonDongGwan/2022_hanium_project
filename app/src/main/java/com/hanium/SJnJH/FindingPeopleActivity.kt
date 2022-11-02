package com.hanium.SJnJH
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import com.hanium.R

class FindingPeopleActivity : AppCompatActivity() {
    lateinit var button4 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finding_people)

        button4 = findViewById(R.id.button4)

        button4.setOnClickListener(){
            finish()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent = Intent(this, MatchingActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}