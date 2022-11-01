package com.hanium.sjnjh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.hanium.R


class LoginActivity : AppCompatActivity() {

    lateinit var idPassTv : TextView
    lateinit var loginTv : TextView
    lateinit var loginBt : Button
    lateinit var idEt : EditText
    lateinit var passEt : EditText
    var token =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        idPassTv = findViewById(R.id.idPassTv)
        loginTv = findViewById(R.id.loginTv)
        loginBt = findViewById(R.id.loginBt)
        idEt = findViewById(R.id.idEt)
        passEt = findViewById(R.id.passEt)

        loginTv.setOnClickListener(){
            val nextIntent = Intent(this, MakeIdActivity::class.java)
            startActivity(nextIntent)
        }

        loginBt.setOnClickListener(){
            val nextIntent = Intent(this, HomeActivity::class.java)
            startActivity(nextIntent)

        }

        idPassTv.setOnClickListener(){
            val nextIntent = Intent(this, IdPassActivity::class.java)
            startActivity(nextIntent)
        }


    }


}