package com.hanium.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R
import com.hanium.activities.ProductActivity
import com.hanium.activities.ProductListActivity
import kotlinx.coroutines.NonDisposableHandle.parent

class HomeViewPagerAdapter(var arrayList: ArrayList<Int>) :
    RecyclerView.Adapter<HomeViewPagerAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img: ImageView = itemView.findViewById(R.id.viewpager_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewPagerAdapter.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: HomeViewPagerAdapter.ViewHolder, position: Int) {
        holder.img.setImageResource(R.drawable.ipad)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context, ProductActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int = arrayList.size
}