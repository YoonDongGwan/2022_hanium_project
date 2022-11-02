package com.hanium.SJnJH

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R


class StoreActivity : AppCompatActivity() {
    lateinit var startBt: Button
    lateinit var rv : RecyclerView
    var arr: ArrayList<ItemData> = ArrayList()
    var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        rv = findViewById(R.id.rv)
        startBt = findViewById(R.id.startBt)

        var adapter : MyRvAdapter = MyRvAdapter(this,arr)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration =
            DividerItemDecoration(rv.context, LinearLayoutManager(this).orientation)

        rv.addItemDecoration(dividerItemDecoration)


        arr.add(ItemData(R.drawable.hoeny,"허니콤보","20,000원"))
        arr.add(ItemData(R.drawable.hoeny,"레드콤보","22,000원"))
        arr.add(ItemData(R.drawable.hoeny,"오리지널","23,000원"))
        arr.add(ItemData(R.drawable.hoeny,"반반","24,000원"))

        adapter.notifyDataSetChanged()


        startBt.setOnClickListener(){
            val nextIntent = Intent(this, FindingPeopleActivity::class.java)
            startActivity(nextIntent)
        }


    }


    inner class MyRvAdapter(val context: Context, val arr: ArrayList<ItemData>) :
        RecyclerView.Adapter<MyRvAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.rec_item, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.iv.setImageResource(arr.get(position).menuImg)
            holder.tv1.setText(arr.get(position).menu)
            holder.tv2.setText(arr.get(position).price)


            holder.chk.setOnClickListener(){
                var menu = arr.get(position).menu
                var priceString = arr.get(position).price

                var price = priceString.split("원")
                var num = price[0]
                Log.d("ssdd","{$menu},가격: $num")
            }


            holder.itemView.setOnClickListener {

            }

        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var chk : CheckBox = itemView!!.findViewById(R.id.chk)
            var iv: ImageView = itemView!!.findViewById(R.id.iv)
            val tv1: TextView = itemView!!.findViewById(R.id.tv1)
            val tv2: TextView = itemView!!.findViewById(R.id.tv2)

        }
    }


}