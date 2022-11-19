package com.hanium.activities

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import com.hanium.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProgressPurchaseActivity : AppCompatActivity() {

    val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_purchase)

        val storeName = findViewById<TextView>(R.id.storeName)
        val person1 = findViewById<TextView>(R.id.person1)
        val person2 = findViewById<TextView>(R.id.person2)
        val person3 = findViewById<TextView>(R.id.person3)
        val person4 = findViewById<TextView>(R.id.person4)
        val personState1 = findViewById<TextView>(R.id.personState1)
        val personState2 = findViewById<TextView>(R.id.personState2)
        val personState3 = findViewById<TextView>(R.id.personState3)
        val personState4 = findViewById<TextView>(R.id.personState4)
        val nowState = findViewById<TextView>(R.id.nowState)

        var name = intent.getStringExtra("name")
        service.getMyProgress(name!!).enqueue(object : Callback<MyProgressResult> {
            override fun onResponse(call: Call<MyProgressResult>, response: Response<MyProgressResult>) {
                if (response.isSuccessful){
                    var result: MyProgressResult? = response.body()
                    var store = result!!.store
                    var date = result!!.date
                    var matchNum = result!!.matchNum
                    storeName.text=store
                    service.getProgressList(store,date,matchNum).enqueue(object : Callback<ProgressListResult> {
                        override fun onResponse(call: Call<ProgressListResult>, response: Response<ProgressListResult>) {
                            if (response.isSuccessful){
                                var result = response.body()
                                val arrayList = result?.data
                                val len = arrayList?.size
                                if(len==2) {
                                    person1.text = arrayList[0].name
                                    personState1.text = arrayList[0].state
                                    person2.text = arrayList[1].name
                                    personState2.text = arrayList[1].state
                                    person3.visibility= View.INVISIBLE
                                    person4.visibility= View.INVISIBLE
                                    personState3.visibility= View.INVISIBLE
                                    personState4.visibility= View.INVISIBLE
                                }
                                else if (len==3){
                                    person3.text=arrayList[2].name
                                    personState3.text=arrayList[2].state
                                    person4.visibility= View.INVISIBLE
                                    personState4.visibility= View.INVISIBLE
                                }
                                else{
                                    person3.text= arrayList!![2].name
                                    personState3.text=arrayList[2].state
                                    person4.text=arrayList[3].name
                                    personState4.text=arrayList[3].state
                                }

                                for (i in 1..(len!! -1)){
                                    if (arrayList[i].state=="배달 중"){
                                        nowState.text="배달 중..."
                                    }
                                    else if(arrayList[i].state=="결제 대기"){
                                        nowState.text="결제 대기 중..."
                                    }
                                    else{
                                        nowState.text="조리 중..."
                                    }
                                }
                            }

                        }
                        override fun onFailure(call: Call<ProgressListResult>, t: Throwable) {
                            Log.d("state", "onFailure" + t.message.toString())
                        }

                    })
                }
            }
            override fun onFailure(call: Call<MyProgressResult>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })

    }
}