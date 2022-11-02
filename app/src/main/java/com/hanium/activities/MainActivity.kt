package com.hanium.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hanium.R
import com.hanium.RetrofitResponse
import com.hanium.RetrofitService
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
    var btns = arrayOfNulls<Button>(8)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewpager = findViewById(R.id.home_viewpager)
        recyclerview = findViewById(R.id.home_recyclerview)
        myPageBtn = findViewById(R.id.myPageBtn)

        for(i in 0..7){
            btns[i] = findViewById(resources.getIdentifier("home_list_btn${i+1}","id",packageName))
            btns[i]?.setOnClickListener(onClickListener)
        }

        val arrayList: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)


        val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)
        service.getBooks().enqueue(object : Callback<RetrofitResponse> {
            override fun onResponse(call: Call<RetrofitResponse>, response: Response<RetrofitResponse>) {
                if (response.isSuccessful){
                    var result: RetrofitResponse? = response.body()
                    val arrayList = result?.data
                    recyclerview.adapter = HomeRecyclerViewAdapter(applicationContext, arrayList)
                    recyclerview.layoutManager = GridLayoutManager(applicationContext, 2)
                }
            }

            override fun onFailure(call: Call<RetrofitResponse>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })
//        recyclerview.adapter = HomeRecyclerViewAdapter(this, arrayList)
//        recyclerview.layoutManager = GridLayoutManager(this, 2)

        viewpager.adapter = HomeViewPagerAdapter(arrayList)
        viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        myPageBtn.setOnClickListener{
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

    }

    private val onClickListener = View.OnClickListener { view ->
        var intent = Intent(this, ProductListActivity::class.java)
        when(view.id){
            R.id.home_list_btn1 -> {
                intent.putExtra("CATEGORY", 1)
            }
            R.id.home_list_btn2 -> {
                intent.putExtra("CATEGORY", 2)
            }
            R.id.home_list_btn8 -> {
                intent = Intent(this, LoginActivity::class.java)
            }
        }
        startActivity(intent)
    }
}