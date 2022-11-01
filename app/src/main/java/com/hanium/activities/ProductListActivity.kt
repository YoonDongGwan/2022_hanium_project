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
import com.hanium.R
import com.hanium.RetrofitResponse
import com.hanium.RetrofitService
import com.hanium.adapters.HomeRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductListActivity : AppCompatActivity() {
     lateinit var backBtn: ImageButton
     lateinit var myClassBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val recyclerview: RecyclerView = findViewById(R.id.product_list_recyclerview)
        backBtn = findViewById(R.id.product_list_back_btn)
        myClassBtn = findViewById(R.id.product_list_myclass_book_btn)

        val intent = intent
        val categoryNum = intent.getIntExtra("CATEGORY", 0)
        Log.d("test", categoryNum.toString())

        backBtn.setOnClickListener(onClickListener)
        myClassBtn.setOnClickListener(onClickListener)

        val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
            .addConverterFactory(GsonConverterFactory.create()).build();
        val service = retrofit.create(RetrofitService::class.java);

        when(categoryNum){
            1 -> {
                myClassBtn.visibility = View.VISIBLE

                service.getBooks().enqueue(object : Callback<RetrofitResponse>{
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
            }
            2 -> {
                service.getElectronics().enqueue(object : Callback<RetrofitResponse>{
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
            }

        }

    }
    val onClickListener = View.OnClickListener { view ->
        when(view.id){
            R.id.product_list_back_btn -> finish()

            R.id.product_list_myclass_book_btn -> {
                val intent = Intent(this, ClassActivity::class.java)
                startActivity(intent)
            }
        }
    }
}