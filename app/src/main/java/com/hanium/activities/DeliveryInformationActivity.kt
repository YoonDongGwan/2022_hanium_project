package com.hanium.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.viewpager2.widget.ViewPager2
import com.hanium.LocationResponse
import com.hanium.R
import com.hanium.RetrofitResponse
import com.hanium.RetrofitService
import com.hanium.adapters.HomeViewPagerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DeliveryInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_information)
        val spinner: Spinner = findViewById(R.id.information_spinner)

        val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)

        service.getDeliveryLocation().enqueue(object : Callback<LocationResponse> {
            override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
                if (response.isSuccessful){
                    var result: LocationResponse? = response.body()
                    val arrayList = result?.data
                    Log.d("test", arrayList!!.get(0).toString())
                    val arrayAdapter = ArrayAdapter<String>(this@DeliveryInformationActivity, R.layout.support_simple_spinner_dropdown_item, arrayList!!)
                    spinner.adapter = arrayAdapter



                }
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })



    }
}