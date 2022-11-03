package com.hanium.SJnJH

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R
import com.hanium.RetrofitResponse
import com.hanium.RetrofitService
import com.hanium.adapters.StoreRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ChickenFragment : Fragment() {


    lateinit var recyclerView1 : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_chicken, container, false)


        recyclerView1 = rootView.findViewById(R.id.recyclerView1!!)as RecyclerView
        recyclerView1.layoutManager = LinearLayoutManager(requireContext())

        val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)
        service.getChickenStores().enqueue(object : Callback<RetrofitResponse> {
            override fun onResponse(call: Call<RetrofitResponse>, response: Response<RetrofitResponse>) {
                if (response.isSuccessful){
                    var result: RetrofitResponse? = response.body()
                    val arrayList = result?.data
                    recyclerView1.adapter = StoreRecyclerViewAdapter(requireContext(),arrayList)


                }
            }

            override fun onFailure(call: Call<RetrofitResponse>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })

        return rootView
    }
}