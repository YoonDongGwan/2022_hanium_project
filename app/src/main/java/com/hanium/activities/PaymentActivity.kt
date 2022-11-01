package com.hanium.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.OrderData
import com.hanium.R

class PaymentActivity : AppCompatActivity() {
    lateinit var rv : RecyclerView
    var oderArr : ArrayList<OrderData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        rv = findViewById(R.id.rv)


        var adapter : MyRvAdapter = MyRvAdapter(this,oderArr)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration =
            DividerItemDecoration(rv.context, LinearLayoutManager(this).orientation)

        rv.addItemDecoration(dividerItemDecoration)

        oderArr.add(OrderData(R.drawable.book,"결제 완료","앱프로그래밍(안드로이드 스튜디오)","20000원","10/21일 도착 예정"))
        oderArr.add(OrderData(R.drawable.book2,"결제 완료","Perfect C(프로그래밍 1)","23000원","10/22일 도착 예정"))



        adapter.notifyDataSetChanged()


    }


    inner class MyRvAdapter(val context: Context, val arr: ArrayList<OrderData>) :
        RecyclerView.Adapter<MyRvAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.order, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.ib.setImageResource(arr.get(position).bookImg)
            holder.tv1.setText(arr.get(position).progress)
            holder.tv2.setText(arr.get(position).content)
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



        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var ib : ImageButton = itemView!!.findViewById(R.id.ib)
            var tv1: TextView = itemView!!.findViewById(R.id.tv1)
            val tv2: TextView = itemView!!.findViewById(R.id.tv2)
            val tv3: TextView = itemView!!.findViewById(R.id.tv3)
            val tv4: TextView = itemView!!.findViewById(R.id.tv4)
            val bt1 : Button = itemView!!.findViewById(R.id.bt1)
            val bt2 : Button = itemView!!.findViewById(R.id.bt2)


        }
    }


}