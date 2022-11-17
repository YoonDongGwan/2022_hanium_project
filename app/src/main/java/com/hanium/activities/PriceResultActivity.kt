package com.hanium.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.hanium.R
import com.hanium.SJnJH.ItemData
import com.hanium.SJnJH.StateData

class PriceResultActivity : AppCompatActivity() {
    lateinit var rv : RecyclerView
    lateinit var rv2: RecyclerView
    lateinit var payBt : Button

    var stateArr : ArrayList<StateData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_result)
        rv = findViewById(R.id.rv)
        rv2 = findViewById(R.id.rv2)
        payBt = findViewById(R.id.payBt)



        payBt.setOnClickListener(){
            val userName = intent.getStringExtra("userName").toString()
            val location = intent.getStringExtra("deliveryPlace").toString()
            val storeName2 = intent.getStringExtra("storeName2").toString()
            Log.d("dddd","msg:$userName,$storeName2,$location")
        }

//        var adapter : MyRvAdapter = MyRvAdapter(this,stateArr)
//        rv.adapter = adapter
//        rv.layoutManager = LinearLayoutManager(this)
//
//        adapter.notifyDataSetChanged()






    }

    inner class MyRvAdapter(val context: Context, val arr: ArrayList<StateData>) :
        RecyclerView.Adapter<MyRvAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.people, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.tv1.setText(arr.get(position).name)
            holder.tv2.setText(arr.get(position).state)


        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var tv1: TextView = itemView!!.findViewById(R.id.tv1)
            val tv2: TextView = itemView!!.findViewById(R.id.tv2)


        }
    }






    fun getPeople(userName : String, location : String,storeName2 : String){
        var url = "http://52.78.209.45:3000/offline/get_people"
        val requestQueue = Volley.newRequestQueue(this)

        val request: StringRequest = object : StringRequest(
            Request.Method.GET, url,request,fail ) {

            override fun getParams(): MutableMap<String, String> {
                val params : MutableMap<String,String> = HashMap()

                params.put("userName",userName)
                params.put("title",location)
                params.put("storeName2",storeName2)

                return params
            }
        }

        requestQueue.add(request)
    }

    var request = object  : Response.Listener<String> {
        override fun onResponse(response: String) {

        }


    }

    var fail = object  : Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {
            Log.d("aabb","서버 연결 실패 : $error")
        }
    }






}















