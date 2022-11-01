package com.hanium.sjnjh

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R


class MenuActivity : AppCompatActivity() {
    lateinit var rv : RecyclerView
    var arr: ArrayList<RoomData> = ArrayList()
    lateinit var backBt : ImageButton
    lateinit var makeBt : RelativeLayout
    lateinit var recommendBt : RelativeLayout
    lateinit var roomTv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        roomTv = findViewById(R.id.roomTv)
        backBt = findViewById(R.id.backBt)
        rv = findViewById(R.id.rv)
        makeBt = findViewById(R.id.makeBt)
        recommendBt = findViewById(R.id.recommendBt)

        recommendBt.setOnClickListener(){
            val nextIntent = Intent(this, BestMenuActivity::class.java)
            startActivity(nextIntent)
        }

        makeBt.setOnClickListener(){
            val nextIntent = Intent(this, MakeRoomDetailActivity::class.java)
            startActivity(nextIntent)
        }

        backBt.setOnClickListener(){
            finish()
            Log.d("ddbb","da")
        }

        roomTv.setOnClickListener(){
            val nextIntent = Intent(this, DeliveryStoresActivity::class.java)
            startActivity(nextIntent)
        }

        rv.setOnClickListener(){
            val nextIntent = Intent(this, DeliveryStoresActivity::class.java)
            startActivity(nextIntent)
        }

        var adapter : MyRvAdapter = MyRvAdapter(this,arr)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        arr.add(RoomData("치킨","허니콤보 드실분"))
        arr.add(RoomData("피자","피자헛 먹자"))
        arr.add(RoomData("햄버거","버거킹 세트"))
        arr.add(RoomData("중식","홍콩반점"))
        arr.add(RoomData("양식","파스타 고"))

        adapter.notifyDataSetChanged()

        backBt.setOnClickListener(){

        }


    }

    inner class MyRvAdapter(val context: Context, val arr: ArrayList<RoomData>) :
        RecyclerView.Adapter<MyRvAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.room, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.tv1.setText(arr.get(position).category)
            holder.tv2.setText(arr.get(position).title)



            holder.tv1.setOnClickListener {
//                Log.d("aabb", "name: " + arr.get(position).name)
//                Toast.makeText(
//                    this@StoreActivity,
//                    "name: " + arr.get(position).name,
//                    Toast.LENGTH_SHORT
//                ).show()
            }

            holder.itemView.setOnClickListener {
                val nextIntent = Intent(context, DeliveryStoresActivity::class.java)
                startActivity(nextIntent)
            }

        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var tv1: TextView = itemView!!.findViewById(R.id.tv1)
            val tv2: TextView = itemView!!.findViewById(R.id.tv2)


        }
    }
}