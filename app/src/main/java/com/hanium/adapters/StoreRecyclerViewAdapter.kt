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
import com.hanium.ProductData
import com.hanium.R
import com.hanium.SJnJH.StoreActivity

class StoreRecyclerViewAdapter(val context: Context, val arrayList: ArrayList<ProductData>?) : RecyclerView.Adapter<StoreRecyclerViewAdapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view){
        val img: ImageView = itemView.findViewById(R.id.store_item_img)
        val name: TextView = itemView.findViewById(R.id.storeName)
        val minPrice: TextView = itemView.findViewById(R.id.minPrice)
        val deliveryTip: TextView = itemView.findViewById(R.id.deliveryTip)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stores, parent, false)
        return Holder(view)
    }
    override fun getItemCount() = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = arrayList!![position].name
        Glide.with(context).load(arrayList[position].imgUrl).into(holder.img)
        holder.minPrice.text = "최소주문금액 ${arrayList[position].minPrice} 원"
        holder.deliveryTip.text = "배달비 ${arrayList[position].deliveryTip} 원"
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView?.context, StoreActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
//        holder.setItem(chickenStoreArray.get(position))
    }
}