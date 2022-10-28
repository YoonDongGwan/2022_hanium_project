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

class Num10Activity : AppCompatActivity() {
    lateinit var rv : RecyclerView
    var purlistArr : ArrayList<PurchaseListData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_num10)

        rv = findViewById(R.id.rv)

        var adapter : MyRvAdapter = MyRvAdapter(this,purlistArr)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration =
            DividerItemDecoration(rv.context, LinearLayoutManager(this).orientation)

        rv.addItemDecoration(dividerItemDecoration)

        purlistArr.add(PurchaseListData(R.drawable.book,"안드로이드 프로그래밍","앱 프로그래밍 책~","정보통신공학과(앱프로그래밍)","평점: (4.5/5)"))
        purlistArr.add(PurchaseListData(R.drawable.book,"Perfect C","프로그래밍1 책~","정보통신공학과(프로그래밍1)","평점: (4.7/5)"))



        adapter.notifyDataSetChanged()

    }


    inner class MyRvAdapter(val context: Context, val arr: ArrayList<PurchaseListData>) :
        RecyclerView.Adapter<MyRvAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.purlist, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.ib.setImageResource(arr.get(position).bookImg)
            holder.tv1.setText(arr.get(position).title)
            holder.tv2.setText(arr.get(position).major)
            holder.tv3.setText(arr.get(position).rate)



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