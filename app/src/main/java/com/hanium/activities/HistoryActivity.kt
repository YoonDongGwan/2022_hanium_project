package com.hanium.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HistoryActivity : AppCompatActivity() {

    val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitService::class.java)

    lateinit var rv : RecyclerView
    var orderListArr : ArrayList<OrderListData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        rv = findViewById(R.id.rv)

        var adapter : MyAdapter = MyAdapter(this,orderListArr)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        val name=getIntent().getStringExtra("name")

        val dividerItemDecoration =
            DividerItemDecoration(rv.context, LinearLayoutManager(this).orientation)

        rv.addItemDecoration(dividerItemDecoration)


        service.getOrderList(name.toString()).enqueue(object : Callback<OrderListResponse> {
            override fun onResponse(
                call: Call<OrderListResponse>,
                response: Response<OrderListResponse>
            ) {
                if (response.isSuccessful) {
                    var result = response.body()
                    val arrayList = result?.data
                    rv.adapter = MyAdapter(this@HistoryActivity, arrayList)

                }
            }

            override fun onFailure(call: Call<OrderListResponse>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })



        adapter.notifyDataSetChanged()

    }


    inner class MyAdapter(val context: Context, val arr: ArrayList<OrderListData>?) :
        RecyclerView.Adapter<MyAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.orderlist, parent, false)
            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr?.size ?: 0
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.storeName.text = arr?.get(position)?.store
            holder.deliveryFood.text = arr?.get(position)?.deliveryFood
            holder.date.text = arr?.get(position)?.date
            holder.price.text ="${arr?.get(position)?.price}원"



//            if(arr.get(position).isChecked == true){
//                holder.chk.isChecked = true
//            }else{
//                holder.chk.isChecked = false
//            }
//
//            holder.chk.setOnClickListener {
//                arr.get(position).isChecked = holder.chk.isChecked
//            }


        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var storeName : TextView = itemView!!.findViewById(R.id.storeName)
            var deliveryFood : TextView = itemView!!.findViewById(R.id.deliveryFood)
            var date : TextView = itemView!!.findViewById(R.id.date)
            var price : TextView = itemView!!.findViewById(R.id.price)


        }
    }


}