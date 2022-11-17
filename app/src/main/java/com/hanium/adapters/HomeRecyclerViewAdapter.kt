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
        val img: ImageView = itemView.findViewById(R.id.main_recyclerview_img)
        val foodName: TextView = itemView.findViewById(R.id.main_recyclerview_food_name)
        val storeName: TextView = itemView.findViewById(R.id.main_recyclerview_store_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewAdapter.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.foodName.text = arrayList!![position].name
        holder.storeName.text = arrayList[position].company
        Glide.with(context).load(arrayList[position].imgUrl).into(holder.img)

    }

    override fun getItemCount(): Int = arrayList!!.size
}