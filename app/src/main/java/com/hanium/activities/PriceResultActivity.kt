package com.hanium.activities

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.hanium.R
import com.hanium.SJnJH.MenuData
import com.hanium.SJnJH.StateData
import org.json.JSONObject

class PriceResultActivity : AppCompatActivity() {
    lateinit var rv : RecyclerView
    lateinit var rv2: RecyclerView
    lateinit var payBt : Button
    lateinit var numTv : TextView
    lateinit var resultTv : TextView
    var deliveryTip = 0
    var stateArr : ArrayList<StateData> = ArrayList()
    var matchNum = 0
    var location =""
    var storeName2 =""
    var userName =""
    var menuArr : ArrayList<MenuData> = ArrayList()
    var size = 0
    var totalPrice = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_result)
        val preferences: SharedPreferences = getSharedPreferences("UserInfo", 0)
        rv = findViewById(R.id.rv)
        rv2 = findViewById(R.id.rv2)
        payBt = findViewById(R.id.payBt)
        numTv = findViewById(R.id.numTv)
        resultTv = findViewById(R.id.resultTv)
        matchNum = intent.getIntExtra("matchNum",0)
        location = intent.getStringExtra("location").toString()
        storeName2 = intent.getStringExtra("storeName2").toString()
        userName = preferences.getString("username",null).toString()
        menuArr = intent.getParcelableArrayListExtra<MenuData>("menuArr")!!

        handler.sendEmptyMessageDelayed(0,500)


        payBt.setOnClickListener(){
            pay()
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
            else {
                holder.tv2.setText("결제완료")
                holder.tv2.setTextColor(Color.parseColor("#FF204996"))
            }



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
            holder.tv2.setText("${arr.get(position).price} 원")


        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var tv1: TextView = itemView!!.findViewById(R.id.tv1)
            val tv2: TextView = itemView!!.findViewById(R.id.tv2)


        }
    }






    fun getPeople(){
        var url = "http://52.78.209.45:3000/delivery/get_people"
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
            size = jsonArray.length()
            var i = 0
            while(i<jsonArray.length()) {
                var tempArray = jsonArray.getJSONObject(i)
                var name = tempArray.getString("name")
                var state = tempArray.getString("state")
                var tempState = StateData(name, state)
                if (name.equals(userName)) {
                    deliveryTip = tempArray.getInt("deliveryTip")
                    totalPrice = tempArray.getInt("totalPrice")
                }
                stateArr.add(tempState)
                i++

            }

            menuArr.removeAt(menuArr.size-1)
            var delivertPrice = deliveryTip/matchNum
            var tempMenu = MenuData("배달비",delivertPrice)
            menuArr.add(tempMenu)
            var sum = totalPrice+delivertPrice
            resultTv.setText("총합 : $sum 원")

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

    fun pay(){
        var url = "http://52.78.209.45:3000/delivery/pay"
        val requestQueue = Volley.newRequestQueue(this)
        val request: StringRequest = object : StringRequest(
            Request.Method.POST, url,request2,fail ) {
            override fun getParams(): MutableMap<String, String> {
                val params : MutableMap<String,String> = HashMap()
                stateArr.clear()
                params.put("matchNum",matchNum.toString())
                params.put("location",location)
                params.put("storeName2",storeName2)
                params.put("userName",userName)
                return params
            }
        }

        requestQueue.add(request)
    }

    var request2 = object  : Response.Listener<String> {
        override fun onResponse(response: String) {


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
            numTv.setText(size.toString()+"/"+matchNum+"명")

            getPeople()
            sendEmptyMessageDelayed(0,5000)

        }
    }






}















