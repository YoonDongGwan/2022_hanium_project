package com.hanium.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
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
import com.hanium.SJnJH.MenuData
import com.hanium.SJnJH.StateData
import org.json.JSONObject

class PriceResultActivity : AppCompatActivity() {
    lateinit var rv : RecyclerView
    lateinit var rv2: RecyclerView
    lateinit var payBt : Button
    var deliveryTip = 0
    var stateArr : ArrayList<StateData> = ArrayList()
    var matchNum = 0
    var location =""
    var storeName2 =""
    var menuArr : ArrayList<MenuData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_result)
        rv = findViewById(R.id.rv)
        rv2 = findViewById(R.id.rv2)
        payBt = findViewById(R.id.payBt)
        matchNum = intent.getIntExtra("matchNum",0)
        location = intent.getStringExtra("location").toString()
        storeName2 = intent.getStringExtra("storeName2").toString()
        menuArr = intent.getParcelableArrayListExtra<MenuData>("menuArr")!!


        handler.sendEmptyMessage(0)
//        getPeople(matchNum, location, storeName2)

        payBt.setOnClickListener(){
//            var i = 0
//            while(i<stateArr.size) {
//                var temp = stateArr.get(i)
//                var name = temp.name
//                var state = temp.state
//                Log.d("ddss","dsd: $name,$state")
//                i++
//            }
        }











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
            if(arr.get(position).state.equals("before"))
                holder.tv2.setText("결제대기")
            else
                holder.tv2.setText("결제완료")


        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var tv1: TextView = itemView!!.findViewById(R.id.tv1)
            val tv2: TextView = itemView!!.findViewById(R.id.tv2)


        }
    }


    inner class MyRvAdapter2(val context: Context, val arr: ArrayList<MenuData>) :
        RecyclerView.Adapter<MyRvAdapter2.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.price, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.tv1.setText(arr.get(position).menu)
            holder.tv2.setText(arr.get(position).price.toString())


        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var tv1: TextView = itemView!!.findViewById(R.id.tv1)
            val tv2: TextView = itemView!!.findViewById(R.id.tv2)


        }
    }






    fun getPeople(matchNum : Int, location : String,storeName2 : String){
        var url = "http://52.78.209.45:3000/offline/get_people"
        val requestQueue = Volley.newRequestQueue(this)

        val request: StringRequest = object : StringRequest(
            Request.Method.POST, url,request,fail ) {

            override fun getParams(): MutableMap<String, String> {
                val params : MutableMap<String,String> = HashMap()
                stateArr.clear()
                params.put("matchNum",matchNum.toString())
                params.put("location",location)
                params.put("storeName2",storeName2)

                return params
            }
        }

        requestQueue.add(request)
    }

    var request = object  : Response.Listener<String> {
        override fun onResponse(response: String) {
            var jsonObject =  JSONObject(response)
            var jsonArray = jsonObject.getJSONArray("data")
            var i = 0
            while(i<jsonArray.length()) {
                var tempArray = jsonArray.getJSONObject(i)
                var name = tempArray.getString("name")
                var state = tempArray.getString("state")
                var tempState = StateData(name, state)
                if (i==0) {
                    deliveryTip = tempArray.getInt("deliveryTip")
                    var totalPrice = tempArray.getInt("totalPrice")
                    Log.d("dddd","msg:$tempArray,$deliveryTip")
                }


                stateArr.add(tempState)
                i++
            }



            var adapter : MyRvAdapter = MyRvAdapter(this@PriceResultActivity,stateArr)
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(this@PriceResultActivity)

            adapter.notifyDataSetChanged()

            var adapter2 : MyRvAdapter2 = MyRvAdapter2(this@PriceResultActivity,menuArr)
            rv2.adapter = adapter2
            rv2.layoutManager = LinearLayoutManager(this@PriceResultActivity)



            adapter2.notifyDataSetChanged()

        }


    }

    var fail = object  : Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {
            Log.d("aabb","서버 연결 실패 : $error")
        }
    }



    var handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            getPeople(matchNum, location, storeName2)
            sendEmptyMessageDelayed(0,1000)

        }
    }






}















