package com.hanium.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R

class BuyingRecyclerViewAdapter(var arrayList: ArrayList<Int>) : RecyclerView.Adapter<BuyingRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = itemView.findViewById(R.id.buying_recyclerview_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.buying_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(R.drawable.ipad)
    }

    override fun getItemCount(): Int = arrayList.size

}