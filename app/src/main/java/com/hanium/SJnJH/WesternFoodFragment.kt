package com.hanium.SJnJH

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R
import com.hanium.RetrofitResponse
import com.hanium.RetrofitService
import com.hanium.adapters.StoreRecyclerViewAdapter
import com.hanium.databinding.ItemStoresBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WesternFoodFragment : Fragment() {

    lateinit var recyclerView : RecyclerView
    val westernFoodStoreArray = mutableListOf<StoreInform>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        var rootView = inflater.inflate(R.layout.fragment_western_food, container, false)
        for (i in 1..100){
            val name = "파스타$i"
            val minPrice = i*100
            val deliveryTip = i*30
            westernFoodStoreArray.add(StoreInform(context?.let {
                AppCompatResources.getDrawable(
                    it,
                    R.drawable.asd
                )
            }!!,name, minPrice,deliveryTip))
        }
        recyclerView = rootView.findViewById(R.id.westernFoodRecyclerView!!)as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)

        service.getWesternStores().enqueue(object : Callback<RetrofitResponse> {
            override fun onResponse(call: Call<RetrofitResponse>, response: Response<RetrofitResponse>) {
                if (response.isSuccessful){
                    var result: RetrofitResponse? = response.body()
                    val arrayList = result?.data
                    recyclerView.adapter = StoreRecyclerViewAdapter(requireContext(),arrayList)
                }
            }

            override fun onFailure(call: Call<RetrofitResponse>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })

        return rootView
    }
}
