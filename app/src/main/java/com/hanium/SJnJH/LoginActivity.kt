package com.hanium.SJnJH

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.hanium.*
import com.hanium.activities.MainActivity
import com.hanium.adapters.StoreRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    lateinit var idPassTv : TextView
    lateinit var loginTv : TextView
    lateinit var loginBt : Button
    lateinit var idEt : EditText
    lateinit var passEt : EditText
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    var token =""

    val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitService::class.java)

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

        loginBt.setOnClickListener() {
            val id = idEt.text.toString()
            val password = passEt.text.toString()

            if(id == "" || password == "") {
                Toast.makeText(applicationContext, "입력하지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginUser = LoginModel(id,password)
            service.login(loginUser).enqueue(object : Callback<LoginResult> {
                override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                    val result = response.body()
                    val user_uid = result?.UID
                    val username = result?.name
                    if (user_uid!=-1){
                        Log.d("login", "성공 +${user_uid}")
                        val nextIntent = Intent(this@LoginActivity, MainActivity::class.java)
                        preferences = getSharedPreferences("UserInfo", 0)
                        editor = preferences.edit()
                        editor.putInt("uid", user_uid!!)
                        editor.putString("username", username)

                        editor.commit()

                        startActivity(nextIntent)
                    }
                    else{
                        Toast.makeText(applicationContext, "로그인 실패, 아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                    Log.d("state", "onFailure" + t.message.toString())
                }

            })

        }

        idPassTv.setOnClickListener(){
            val nextIntent = Intent(this, IdPassActivity::class.java)
            startActivity(nextIntent)
        }


    }


}