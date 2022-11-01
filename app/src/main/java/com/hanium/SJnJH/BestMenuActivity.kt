package com.hanium.SJnJH

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R


class BestMenuActivity : AppCompatActivity() {
    lateinit var rv : RecyclerView
    lateinit var backBt1 : ImageButton
    var arr: ArrayList<StoreData> = ArrayList()
    lateinit var chatBt : Button
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_menu)


        backBt1 = findViewById(R.id.backBt1)
        rv = findViewById(R.id.rv)
        spinner = findViewById(R.id.spinner)
        spinner.adapter = ArrayAdapter.createFromResource(this, R.array.itemList,android.R.layout.simple_spinner_item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
//        chatBt = findViewById(R.id.chatBt)

        arr.add(StoreData("네네 치킨","4.7/5","최소주문 12000원","배달비 4000원"))
        arr.add(StoreData("굽네 치킨","4.8/5","최소주문 15000원","배달비 3000원"))
        arr.add(StoreData("BBQ","4.1/5","최소주문 18000원","배달비 2000원"))
        arr.add(StoreData("지코바 치킨","4.0/5","최소주문 16000원","배달비 1000원"))
        arr.add(StoreData("BHC","4.0/5","최소주문 16000원","배달비 1000원"))
        arr.add(StoreData("노랑통닭","4.0/5","최소주문 16000원","배달비 1000원"))
        arr.add(StoreData("싸바싸바","4.0/5","최소주문 16000원","배달비 1000원"))
        arr.add(StoreData("네네 치킨","4.0/5","최소주문 16000원","배달비 1000원"))

        var adapter : MyRvAdapter = MyRvAdapter(this,arr)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        DividerItemDecoration.VERTICAL
        adapter.notifyDataSetChanged()

        val dividerItemDecoration =
            DividerItemDecoration(rv.context, LinearLayoutManager(this).orientation)

        rv.addItemDecoration(dividerItemDecoration)

//        mMessageRecyclerView.addItemDecoration(
//            DividerItemDecoration(
//                applicationContext,
//                DividerItemDecoration.VERTICAL
//            )
//        )

        backBt1.setOnClickListener(){
            finish()
        }




    }

    inner class MyRvAdapter(val context: Context, val arr: ArrayList<StoreData>) :
        RecyclerView.Adapter<MyRvAdapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.room2, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.tv1.setText(arr.get(position).store)
            holder.tv2.setText(arr.get(position).rank)
            holder.tv3.setText(arr.get(position).fee)
            holder.tv4.setText(arr.get(position).delivery)

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
                Log.d("aabb", "name: " + arr.get(position).store)
                Toast.makeText(
                    this@BestMenuActivity,
                    "name: " + arr.get(position).store,
                    Toast.LENGTH_SHORT
                ).show()
            }

            holder.itemView.setOnClickListener {
                Log.d("aabb", "아이템 클릭!!")
                Toast.makeText(this@BestMenuActivity, "아이템 클릭!!", Toast.LENGTH_SHORT).show()
            }

        }

        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
            var tv1: TextView = itemView!!.findViewById(R.id.tv1)
            val tv2: TextView = itemView!!.findViewById(R.id.tv2)
            val tv3: TextView = itemView!!.findViewById(R.id.tv3)
            val tv4: TextView = itemView!!.findViewById(R.id.tv4)


        }
    }


}