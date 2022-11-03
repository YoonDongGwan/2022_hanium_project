package com.hanium.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hanium.ResponseData
import com.hanium.R
import com.hanium.activities.ProductActivity

class HomeRecyclerViewAdapter(val context: Context, val arrayList: ArrayList<ResponseData>?) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img: ImageView = itemView.findViewById(R.id.product_recyclerview_img)
        val name: TextView = itemView.findViewById(R.id.product_recyclerview_name)
        val price: TextView = itemView.findViewById(R.id.product_recyclerview_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewAdapter.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.name.text = arrayList!![position].name
        Glide.with(context).load(arrayList[position].imgUrl).into(holder.img)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context, ProductActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int = arrayList!!.size
}