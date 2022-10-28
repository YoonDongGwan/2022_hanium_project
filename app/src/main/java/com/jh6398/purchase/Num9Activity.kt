package com.jh6398.purchase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Num9Activity : AppCompatActivity() {
    lateinit var rv : RecyclerView
    var presentArr : ArrayList<PresentData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_num9)

        rv = findViewById(R.id.rv)

        var adapter : MyRvAdapter = MyRvAdapter(this,presentArr)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration =
            DividerItemDecoration(rv.context, LinearLayoutManager(this).orientation)

        rv.addItemDecoration(dividerItemDecoration)

        presentArr.add(PresentData(R.drawable.book,"안드로이드 프로그래밍","현재 참여중인 인원 : (1/3)","23:59:59"))
        presentArr.add(PresentData(R.drawable.book2,"Perfect C","현재 참여중인 인원 : (2/3)","13:39:52"))



        adapter.notifyDataSetChanged()


    }


    inner class MyRvAdapter(val context: Context, val arr: ArrayList<PresentData>) :
        RecyclerView.Adapter<MyRvAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.present, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.ib.setImageResource(arr.get(position).bookImg)
            holder.tv1.setText(arr.get(position).title)
            holder.tv2.setText(arr.get(position).presentNum)
            holder.tv3.setText(arr.get(position).time)



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


        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var ib : ImageButton = itemView!!.findViewById(R.id.ib)
            var tv1: TextView = itemView!!.findViewById(R.id.tv1)
            val tv2: TextView = itemView!!.findViewById(R.id.tv2)
            val tv3: TextView = itemView!!.findViewById(R.id.tv3)
            val bt1: TextView = itemView!!.findViewById(R.id.bt1)


        }
    }


}