package com.hanium.SJnJH

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hanium.*
import com.hanium.activities.MainActivity
import com.hanium.databinding.ActivityMakeIdBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MakeIdActivity : AppCompatActivity(){
    val binding by lazy{ ActivityMakeIdBinding.inflate(layoutInflater)}

    val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnMakeId.setOnClickListener(){

            val name = binding.editName.text.toString()
            val id = binding.editId.text.toString()
            val password = binding.editPW.text.toString()
            val confirmPassword = binding.editConfirmPW.text.toString()
            val school = binding.editSchool.text.toString()
            val major = binding.editMajor.text.toString()

            if(name == "" || id == "" || password == "" || confirmPassword == "" || school == "" || major == "") {
                Toast.makeText(applicationContext, "입력하지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.equals(confirmPassword)) {
                val newUser = SignUpModel(name, id, password, school, major)
                service.signUp(newUser).enqueue(object : Callback<SignUpResult> {
                    override fun onResponse(
                        call: Call<SignUpResult>,
                        response: Response<SignUpResult>
                    ) {
                        val result = response.body()?.message ?: return
                        if (result) {
                            val nextIntent = Intent(this@MakeIdActivity, LoginActivity::class.java)
                            startActivity(nextIntent)
                            Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        } else
                            Toast.makeText(
                                applicationContext,
                                "회원가입 실패, 이미 존재하는 아이디 입니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                    }

                    override fun onFailure(call: Call<SignUpResult>, t: Throwable) {
                        Log.d("state", "onFailure" + t.message.toString())
                    }

                })
            }
            else{
                Toast.makeText(applicationContext, "비밀번호가 서로 다릅니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

    }


}