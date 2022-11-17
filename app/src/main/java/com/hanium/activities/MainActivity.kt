package com.hanium.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hanium.Chat.ChatRoomListActivity
import com.hanium.R
import com.hanium.RetrofitResponse
import com.hanium.RetrofitService
import com.hanium.SJnJH.DeliveryStoresActivity
import com.hanium.SJnJH.LoginActivity
import com.hanium.adapters.HomeRecyclerViewAdapter
import com.hanium.adapters.HomeViewPagerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewpager: ViewPager2
    lateinit var recyclerview: RecyclerView
    lateinit var myPageBtn: ImageButton
    lateinit var chatBtn: FloatingActionButton
    var btns = arrayOfNulls<LinearLayout>(8)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewpager = findViewById(R.id.home_viewpager)
        recyclerview = findViewById(R.id.home_recyclerview)
        myPageBtn = findViewById(R.id.myPageBtn)
        chatBtn = findViewById(R.id.main_chat_btn)

        for(i in 0..7){
            btns[i] = findViewById(resources.getIdentifier("home_list_btn${i+1}","id",packageName))
            btns[i]?.setOnClickListener(onClickListener)
        }



        val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)
        service.getFoodRanking().enqueue(object : Callback<RetrofitResponse> {
            override fun onResponse(call: Call<RetrofitResponse>, response: Response<RetrofitResponse>) {
                if (response.isSuccessful){
                    var result: RetrofitResponse? = response.body()
                    val arrayList = result?.data
                    viewpager.adapter = HomeViewPagerAdapter(applicationContext, arrayList)
                    viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                }
            }

            override fun onFailure(call: Call<RetrofitResponse>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })
        service.randomRecommend().enqueue(object : Callback<RetrofitResponse> {
            override fun onResponse(call: Call<RetrofitResponse>, response: Response<RetrofitResponse>) {
                if (response.isSuccessful){
                    var result: RetrofitResponse? = response.body()
                    val arrayList = result?.data

                    recyclerview.adapter = HomeRecyclerViewAdapter(this@MainActivity, arrayList)
                    recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

                }
            }

            override fun onFailure(call: Call<RetrofitResponse>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })


        myPageBtn.setOnClickListener{
            val intent = Intent(this, MyPageActivity::class.java)

            startActivity(intent)
        }
        chatBtn.setOnClickListener{
            val intent = Intent(this, ChatRoomListActivity::class.java)
            startActivity(intent)
        }
    }

    private val onClickListener = View.OnClickListener { view ->
        var intent = Intent(this, DeliveryStoresActivity::class.java)
        when(view.id){
            R.id.home_list_btn1 -> {
                intent.putExtra("CATEGORY", 0)
            }
            R.id.home_list_btn2 -> {
                intent.putExtra("CATEGORY", 1)
            }
            R.id.home_list_btn3 -> {
                intent.putExtra("CATEGORY", 2)
            }
            R.id.home_list_btn4 -> {
                intent.putExtra("CATEGORY", 3)
            }
            R.id.home_list_btn5 -> {
                intent.putExtra("CATEGORY", 4)
            }
            R.id.home_list_btn6 -> {
                intent.putExtra("CATEGORY", 5)
            }
            R.id.home_list_btn7 -> {
                intent.putExtra("CATEGORY", 6)
            }
            R.id.home_list_btn8 -> {
                intent = Intent(this, MapActivity::class.java)
            }
        }
        startActivity(intent)
    }
}