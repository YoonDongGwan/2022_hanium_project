package com.jh6398.purchase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Num5Activity : AppCompatActivity() {

    var arr : ArrayList<BookData> = ArrayList()
    lateinit var rv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_num5)
        rv = findViewById(R.id.rv)

        var adapter : MyRvAdapter = MyRvAdapter(this,arr)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration =
            DividerItemDecoration(rv.context, LinearLayoutManager(this).orientation)

        rv.addItemDecoration(dividerItemDecoration)

        arr.add(BookData(R.drawable.book,"안드로이드 프로그래밍","앱프로그래밍","20000원"))
        arr.add(BookData(R.drawable.book2,"Perfect C","프로그래밍1","23000원"))



        adapter.notifyDataSetChanged()


    }


    inner class MyRvAdapter(val context: Context, val arr: ArrayList<BookData>) :
        RecyclerView.Adapter<MyRvAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.ib.setImageResource(arr.get(position).bookImg)
            holder.tv1.setText(arr.get(position).title)
            holder.tv2.setText(arr.get(position).className)
            holder.tv3.setText(arr.get(position).price)



//            if(arr.get(position).isChecked == true){
//                holder.chk.isChecked = true
//            }else{
//                holder.chk.isChecked = false
//            }
//
//            holder.chk.setOnClickListener {
//                arr.get(position).isChecked = holder.chk.isChecked
//            }

            holder.tv1.setOnClickListener {

            }

            holder.itemView.setOnClickListener {
//                Toast.makeText(this@Num5Activity, "아이템 클릭!!", Toast.LENGTH_SHORT).show()
            }

            holder.check.setOnClickListener {
//                Toast.makeText(this@Num5Activity, "체크박스 클릭!!", Toast.LENGTH_SHORT).show()
            }

        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var ib : ImageButton = itemView!!.findViewById(R.id.ib)
            var check : CheckBox = itemView!!.findViewById(R.id.check)
            var tv1: TextView = itemView!!.findViewById(R.id.tv1)
            val tv2: TextView = itemView!!.findViewById(R.id.tv2)
            val tv3: TextView = itemView!!.findViewById(R.id.tv3)



        }
    }




}