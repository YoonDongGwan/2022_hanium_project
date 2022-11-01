package com.hanium.sjnjh
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    }
}